package edu.hm.swe.eam.controller;

import edu.hm.swe.eam.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Mario
 * Date: 28.11.2017
 */
@RestController
public class MailController {

    private static final Logger LOGGER = LogManager.getLogger(MailController.class);


    @RequestMapping(Constants.API_BASE + "/mailAddress")
    public void mail(@RequestBody String mailAddress) {
        LOGGER.info("Transmitted mailAddress:" + mailAddress);
    }

}


