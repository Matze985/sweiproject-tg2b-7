package edu.hm.sweI.eam.controller;

import edu.hm.sweI.eam.mail.Gmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ContactController {

    @Value("${gmail.username}")
    String gmailUsername;

    @Value("${gmail.password}")
    String gmailPassword;

    @Resource
    private Environment environment;
    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);


    @RequestMapping("/api/contact_mailAddress")
    public void mail(
            @RequestParam(value = "contact_title") String contact_title,
            @RequestParam(value = "contact_description") String contact_description,
            @RequestParam(value = "contact_email") String contact_email){
        LOGGER.info("Transmitted Title:" + contact_title);
        LOGGER.info("Transmitted Description:" + contact_description);
        LOGGER.info("Transmitted mailAddress:" + contact_email);

        // Get Environment Variables Username, Password
        LOGGER.info("Environment Variables:" + environment.getProperty("GMAIL_USERNAME"));
        LOGGER.info("Environment Variables:" + environment.getProperty("GMAIL_PASSWORD"));

        // Send Mail to International Office
        Gmail gmail = new Gmail(gmailUsername, gmailPassword, gmailUsername, "" + contact_email, "" + contact_title, "" + contact_description);
        gmail.send();
    }

}
