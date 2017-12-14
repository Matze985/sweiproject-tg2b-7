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


    @RequestMapping("/api" + "/contact_mailAddress")
    public void mail(@RequestParam(value = "contact_mailAddress") String contact_mailAddress){
        LOGGER.info("Transmitted mailAddress:" + contact_mailAddress);
    }

}
