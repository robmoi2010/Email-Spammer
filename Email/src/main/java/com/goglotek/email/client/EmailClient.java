package com.goglotek.email.client;

import com.goglotek.email.AttachmentFile;
import com.goglotek.email.model.Attachment;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class EmailClient {

  @Value("${application.custom.email.from_address}")
  private String emailID;

  @Value("${application.custom.email.password}")
  private String password;

  @Value("${application.custom.email.smtp_host_server}")
  private String smtpHostServer;

  @Value("${application.custom.email.smtp_port}")
  private String smtpPort;

  public boolean sendEmail(String subject, String msg, String to, String[] attachmentDir,
      List<AttachmentFile> attachments)
      throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.host", smtpHostServer);
    props.put("mail.smtp.port", smtpPort);
    props.put("mail.smtp.user", emailID);

    // Get the Session object.
    Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailID, password);
          }
        });
    // Create a default MimeMessage object.
    Message message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(emailID));

    message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to));

    // Set Subject: header field
    message.setSubject(subject);

    // Create the message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Now set the actual message
    messageBodyPart.setText(msg);

    // Create a multipar message
    Multipart multipart = new MimeMultipart();

    // Set text message part
    multipart.addBodyPart(messageBodyPart);

    messageBodyPart = new MimeBodyPart();

    if (attachmentDir != null) {
      for (String dir : attachmentDir) {
        DataSource source = new FileDataSource(dir);
        messageBodyPart.setDataHandler(new DataHandler(source));
        String[] arr = dir.split("\\\\");
        messageBodyPart.setFileName(arr[arr.length - 1]);
        multipart.addBodyPart(messageBodyPart);
      }
    }
    if (attachments != null) {
      for (AttachmentFile af : attachments) {
        DataSource source = new ByteArrayDataSource(af.getData(),
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(af.getFileName());
        multipart.addBodyPart(messageBodyPart);
      }
    }

    // Send the complete message parts
    message.setContent(multipart);

    // Send message
    Transport.send(message);

    return true;
  }
}
