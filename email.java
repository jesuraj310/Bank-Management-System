import javax.mail.PasswordAuthentication;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
class Email {
    static final String USERNAME = "jadeja0310@gmail.com";
    static final String PASSWORD = "j.jesu310";

    public static void main(String args[]) throws Exception {
        String mailFrom = "hackerboytom@gmail.com";
        String mailTo = "1912060@nec.edu.in";
        String mailSubject = "Turn Behind";
        String mailText="Hello";
        
        Email gmail = new Email();
        gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
    }

    public void sendMail(String mailFrom, String mailTo, String mailSubject,
            String mailText) throws Exception {

        Properties config = createConfiguration();

        // Creates a mail session. We need to supply username and
        // password for Gmail authentication.
        Session session = Session.getInstance(config, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        Email.USERNAME,
                        Email.PASSWORD);
            }
        });

        // Creates email message
        Message message = new MimeMessage(session);
        message.setSentDate(new Date());
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(mailTo));
        message.setSubject(mailSubject);
        message.setText(mailText);

        // Send a message
        Transport.send(message);
    }

    public Properties createConfiguration() {
        return new Properties() {
            {
                put("mail.smtp.auth", "true");
                put("mail.smtp.host", "smtp.gmail.com");
                put("mail.smtp.port", "587");
                put("mail.smtp.starttls.enable", "true");
                put("mail.smtp.ssl.protocols", "TLSv1.2");
            }
        };
    }
}