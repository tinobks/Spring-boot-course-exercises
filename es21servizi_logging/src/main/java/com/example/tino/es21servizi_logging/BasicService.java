package com.example.tino.es21servizi_logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

    private final Logger logger = LoggerFactory.getLogger(BasicController.class);

    public double calculatePower(int a, int b) {
        logger.info("Calculating power...");
        double result = Math.pow(a, b);
        logger.info("Result : {}", result);
        return result;
    }
}
