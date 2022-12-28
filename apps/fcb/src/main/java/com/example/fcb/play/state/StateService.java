package com.example.fcb.play.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
class StateService {

    private Logger logger = LoggerFactory.getLogger(StateService.class);

    public void saveSate(StateCommand command) {
        String first = command.getUuid();
        assert command.getContent().equals(first);
        logger.info("uuid {}", command.getUuid());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        // java assert is disabled by default while running in production
        assert command.getContent().equals(first);
        String second = command.getUuid();
        // use Spring assert utils for validation purposes.
        Assert.isTrue(first.equals(second), "first and second uuid should be same");
        logger.info("uuid {}", command.getUuid());
    }
}
