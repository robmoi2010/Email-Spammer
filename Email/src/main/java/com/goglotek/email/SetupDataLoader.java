package com.goglotek.email;


import com.goglotek.email.client.EmailClient;
import com.goglotek.email.model.*;
import com.goglotek.email.service.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.hpsf.Section;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private CompanyService companyService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private EmailClient emailClient;

  @Autowired
  private TemplateService templateService;
  @Autowired
  private AttachmentService attachmentService;
  @Autowired
  private OutboxService outboxService;

  @Autowired
  private AccountTemplateService accountTemplateService;

  @Autowired
  private OutboxAttachmentService outboxAttachmentService;

  @Autowired
  private AttachmentAccountTemplateService attachmentAccountTemplateService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    sendEmails();
    //loadContacts();
  }

  @Transactional
  public void sendEmails() {
    List<Company> companyList = companyService.findAllActive();
    for (Company c : companyList) {
      try {
        List<Account> accountList = accountService.findByCompany(c);
        for (int i = 0; i < accountList.size(); i++) {
          Account account = accountList.get(i);
          if (account.isActive()) {
            if (account.getEmailAddress() != null && !account.getEmailAddress().isEmpty()) {
              List<AccountTemplate> accountTemplateList = accountTemplateService.findByAccount(account);
              for (AccountTemplate t : accountTemplateList) {
                try {
                  List<AttachmentAccountTemplate> attachmentATList = attachmentAccountTemplateService.findByAccountTemplate(
                      t);
                  Outbox outbox = new Outbox();
                  outbox.setAccount(account);
                  outbox.setMessage(t.getTemplate().getTemplate());
                  outbox.setCreatedDate(new Date());
                  outbox.setSendSuccessful(false);
                  outbox.setModifiedDate(new Date());
                  outbox = outboxService.save(outbox);
                  for (AttachmentAccountTemplate a : attachmentATList) {
                    OutboxAttachment outboxAttachment = new OutboxAttachment();
                    outboxAttachment.setAttachment(a.getAttachment());
                    outboxAttachment.setOutbox(outbox);
                    outboxAttachmentService.save(outboxAttachment);
                  }
                  String message = t.getTemplate().getTemplate();
                  if (message.contains("[company]")) {
                    message = message.replace("[company]", c.getName()).trim();
                  }
                  List<AttachmentFile> af = new ArrayList<>();
                  for (AttachmentAccountTemplate aat : attachmentATList) {
                    Attachment a = aat.getAttachment();
                    if (a.getTemplate()) {
                      byte[] newData = editDocument(a.getFilePath(), "[company]", c.getName());
                      AttachmentFile f = new AttachmentFile();
                      String[] arr = a.getFilePath().split("\\\\");
                      String fileName = arr[arr.length - 1].replace("[company]", c.getName());
                      f.setFileName(fileName);
                      f.setData(newData);
                      af.add(f);
                    }
                  }
                  boolean sentSuccessfuly = false;
                  if (af.isEmpty()) {
                    sentSuccessfuly = emailClient.sendEmail(t.getTemplate().getSubject(), message,
                        account.getEmailAddress(),
                        attachmentATList.stream().map(s -> s.getAttachment().getFilePath()).toArray(String[]::new),
                        null);
                  } else {
                    sentSuccessfuly = emailClient.sendEmail(t.getTemplate().getSubject(), message,
                        account.getEmailAddress(),
                        null, af);
                  }
                  if (sentSuccessfuly) {
                    outbox.setSendSuccessful(true);
                    outboxService.save(outbox);
                    account.setOutboxCount(account.getOutboxCount() + 1);
                    accountService.save(account);
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void loadContacts() {
    CSVParser parser = new CSVParserBuilder()
        .withSeparator(',')
        .withIgnoreQuotations(true)
        .build();
    try (Reader reader = Files.newBufferedReader(Paths.get("D:\\Goglotek\\Documents\\Sales\\B2C.csv"))) {
      CSVReader csvReader = null;
      try {
        csvReader = new CSVReaderBuilder(reader)
            .withSkipLines(0)
            .withCSVParser(parser)
            .build();
        String[] record = null;
        while ((record = csvReader.readNext()) != null) {
          try {
            Company c = new Company();
            c.setActive(true);
            c.setCreatedDate(new Date());
            c.setName(record[0].trim());
            c.setModifiedDate(new Date());
            c = companyService.save(c);
            Account ac = new Account();
            ac.setCompany(c);
            ac.setActive(true);
            ac.setEmailAddress(record[2].trim());
            ac.setPhoneNumber(record[1].split(".\\/"));
            ac.setOutboxCount(0L);
            ac.setCreatedDate(new Date());
            ac.setModifiedDate(new Date());
            accountService.save(ac);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (csvReader != null) {
          csvReader.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private byte[] editDocument(String docPath, String oldText, String newText) throws Exception {
    ByteArrayOutputStream outputStream = null;
    newText = newText.trim();
    try {
      outputStream = new ByteArrayOutputStream();
      XWPFDocument doc = new XWPFDocument(OPCPackage.open(docPath));
      for (XWPFParagraph p : doc.getParagraphs()) {
        List<XWPFRun> runs = p.getRuns();
        if (runs != null) {
          for (XWPFRun r : runs) {
            String text = r.getText(0);
            if (text != null && text.contains(oldText)) {
              text = text.replace(oldText, newText);
              r.setText(text, 0);
            }
          }
        }
      }
      for (XWPFTable tbl : doc.getTables()) {
        for (XWPFTableRow row : tbl.getRows()) {
          for (XWPFTableCell cell : row.getTableCells()) {
            for (XWPFParagraph p : cell.getParagraphs()) {
              for (XWPFRun r : p.getRuns()) {
                String text = r.getText(0);
                if (text != null && text.contains(oldText)) {
                  text = text.replace(oldText, newText);
                  r.setText(text, 0);
                }
              }
            }
          }
        }
      }
      doc.write(outputStream);
      byte[] data = outputStream.toByteArray();
      return data;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      outputStream.close();
    }

  }
}
