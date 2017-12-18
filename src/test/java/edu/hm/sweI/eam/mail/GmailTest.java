package edu.hm.sweI.eam.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class GmailTest {

    private String username = "testUser";
    private String password = "testPassword";
    private String mailAddressTo = "testMailTo";
    private String mailAddressFrom = "testMailFrom";
    private String subject = "testSubject";
    private String text = "testText";

    @Test
    public void gmailGetsInitializedCorrectly() {
        Gmail gmail = new Gmail(username, password, mailAddressTo, mailAddressFrom, subject, text);

        assertEquals(username, gmail.getUsername());
        try {
            Field fieldPassword = gmail.getClass().getDeclaredField("password");
            fieldPassword.setAccessible(true);
            assertEquals(password, fieldPassword.get(gmail));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(mailAddressTo, gmail.getMailAddressTo());
        assertEquals(mailAddressFrom, gmail.getMailAddressFrom());
        assertEquals(subject, gmail.getSubject());
        assertEquals(text, gmail.getText());
    }

    @Test
    public void createMessageIsCreatingCorrectMessage() throws MessagingException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Gmail gmail = new Gmail(username, password, mailAddressTo, mailAddressFrom, subject, text);
        Method createMessage = Gmail.class.getDeclaredMethod("createMessage");
        createMessage.setAccessible(true);
        Message message = (Message) createMessage.invoke(gmail);

        assertNotNull(message);
        assertEquals(mailAddressTo, message.getAllRecipients()[0].toString());
        assertTrue(message.getFrom()[0].toString().contains(mailAddressFrom));
        assertEquals(subject, message.getSubject());
    }
}
