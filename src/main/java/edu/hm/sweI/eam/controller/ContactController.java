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


    @RequestMapping(API_BASE + "/contact_mailAddress?contact_email")
    //@RequestMapping("api" + "/contact_mailAddress")
    public void mail(@RequestParam(value = "contact_email") String contact_email){
        LOGGER.info("Transmitted mailAddress:" + contact_email);
    }
}
