package com.shreyas.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/loggingApp")
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping()
    public String getLoggerName() {
        logger.info("In Controller now");
        return "Shreyas";
    }
}
