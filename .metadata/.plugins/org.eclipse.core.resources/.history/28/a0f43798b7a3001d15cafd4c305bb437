package com.Redit.clone.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.Redit.clone.Exceptions.SpringRedditException;
import com.Redit.clone.Model.NotificationEmail;
import com.Redit.clone.Service.MailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender javaMailSender;
 
	@Override
	public void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper mesageHelper = new MimeMessageHelper(mimeMessage);
			mesageHelper.setFrom("bouachirhamza0@gmail.com");
			mesageHelper.setTo(notificationEmail.getRecipient());
			mesageHelper.setSubject(notificationEmail.getSubject());
			mesageHelper.setText(notificationEmail.getBody());

		};

		try {
			javaMailSender.send(messagePreparator);
			log.info("Mail envoyer avec succés");
		} catch (MailException e) {
          throw new SpringRedditException("Exception occured when sending mail to"+" "+notificationEmail.getRecipient());
		}

	}

}
