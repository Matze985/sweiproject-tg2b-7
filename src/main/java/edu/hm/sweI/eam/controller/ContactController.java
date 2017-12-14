package edu.hm.sweI.eam.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static edu.hm.sweI.eam.controller.Constants.API_BASE;


@RestController
public class ContactController {

    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);


    @RequestMapping(API_BASE + "/contact_mailAddress")
    public void mail(
            @RequestParam(value = "contact_title") String contact_title,
            @RequestParam(value = "contact_description") String contact_description,
            @RequestParam(value = "contact_email") String contact_email){
        LOGGER.info("Transmitted Title:" + contact_title);
        LOGGER.info("Transmitted Description:" + contact_description);
        LOGGER.info("Transmitted mailAddress:" + contact_email);

        // Get Environment Variables Username, Password
        // Send Mail to International Office
    }

}
