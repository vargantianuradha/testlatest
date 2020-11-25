package ca.cityofkingston.payments;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;

public class Messenger {


	public void send(String messageBody, String subject)  {

		MailMessage makeMailMessage = makeMailMessage();
		
		fillMessage(messageBody, subject, makeMailMessage);
		
		MailServiceUtil.sendEmail(makeMailMessage);
	}

	private void fillMessage(String messageBody, String subject, MailMessage makeMailMessage) {
		
		try {
			
			makeMailMessage.setBody(messageBody);
			makeMailMessage.setSubject(subject);
			//FIXME: RV externalize email address?
			makeMailMessage.setFrom(new InternetAddress("ritwiksoft2018@gmail.com"));
			makeMailMessage.setTo(new InternetAddress("ritwiksoft2018@gmail.com"));
			makeMailMessage.setCC(new InternetAddress("ritwiksoft2018@gmail.com"));
			makeMailMessage.setHTMLFormat(true);
			
		} catch (AddressException e) {
			throw new RuntimeException(e);
		}
	}

	MailMessage makeMailMessage() {
		return new MailMessage();
	}

	MailMessage makeMailMessage(String body, String subject) {
		MailMessage mailMessage = makeMailMessage();
		mailMessage.setSubject(subject);
		return mailMessage;
	}



}
