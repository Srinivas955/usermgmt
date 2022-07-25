package in.srinivas.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender sendEmail;
		
	public boolean mailsender(String to, String Subject, String Body ) {
		boolean isSent = false;
	try 
		{
		MimeMessage createMimeMessage = sendEmail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(createMimeMessage);
		helper.setTo(to);
		helper.setSubject(Subject);
		helper.setText(Body, true);
		sendEmail.send(createMimeMessage);
		isSent = true;
		}catch(Exception e){
			e.printStackTrace();
			  }
		
		return isSent;
		
	}
	

}
