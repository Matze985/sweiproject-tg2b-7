package edu.hm.sweI.eam.mail;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class Gmail {

    private String username;
    private String password;
    private String mailAddressTo;
    private String mailAddressFrom;
    private String subject;
    private String text;


    private static final Logger LOGGER = LogManager.getLogger(Gmail.class);

    public Gmail(String username, String password, String mailAddressTo, String mailAddressFrom, String subject, String text) {
        this.username = username;
        this.password = password;
        this.mailAddressTo = mailAddressTo;
        this.mailAddressFrom = mailAddressFrom;
        this.subject = subject;
        this.text = text;
    }

    public void send() {

        //final String username = "sweiproject.tg2b.7@gmail.com";
        //final String password = "softwareengineering1";

        LOGGER.info(username +" "+ password+" "+mailAddressTo+ " "+mailAddressFrom +" "+subject+ " "+text);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""+mailAddressFrom, ""+mailAddressFrom));//new InternetAddress(mailAddressFrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(""+mailAddressTo));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

