
UPDATE template SET active=false WHERE active=true;
INSERT INTO template(
	template_id, created_date, modified_date, template, subject, active)
	VALUES (nextval('template_sq'), now(), now(), '', '', true);
INSERT INTO account_template(template_id, account_id, account_template_id)
SELECT (SELECT template_id FROM template WHERE active=true ORDER BY created_date DESC LIMIT 1), account_id, nextval('account_template_sq')
FROM account;
INSERT INTO attachment_account_template(attachment_account_template_id, attachment_id, account_template_id)
SELECT nextval('attachment_account_template_sq'), (
  select att.attachment_id from attachment att 
  inner join attachment_account_template aat on att.attachment_id=aat.attachment_id
  inner join account_template atlt on atlt.account_template_id=aat.account_template_id
  inner join account acc2 on acc2.account_id=atlt.account_id
  WHERE acc2.account_id=ac.account_id LIMIT 1
), account_template_id
	FROM account_template a 
	inner join template t on a.template_id=t.template_id
	inner join account ac on ac.account_id=a.account_id
	where t.active=true



