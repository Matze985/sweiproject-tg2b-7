package edu.hm.sweI.eam.mail;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
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
        LOGGER.info(username +" "+ password+" "+mailAddressTo+ " "+mailAddressFrom +" "+subject+ " "+text);
        try {

            Message message = createMessage();

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage() throws UnsupportedEncodingException, MessagingException {
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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("" + mailAddressFrom, "" + mailAddressFrom));//new InternetAddress(mailAddressFrom));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("" + mailAddressTo));
        message.setSubject(subject);
        message.setText(text);

        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getMailAddressTo() {
        return mailAddressTo;
    }

    public String getMailAddressFrom() {
        return mailAddressFrom;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gmail gmail = (Gmail) o;
        return Objects.equals(username, gmail.username) &&
                Objects.equals(password, gmail.password) &&
                Objects.equals(mailAddressTo, gmail.mailAddressTo) &&
                Objects.equals(mailAddressFrom, gmail.mailAddressFrom) &&
                Objects.equals(subject, gmail.subject) &&
                Objects.equals(text, gmail.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, mailAddressTo, mailAddressFrom, subject, text);
    }
}

