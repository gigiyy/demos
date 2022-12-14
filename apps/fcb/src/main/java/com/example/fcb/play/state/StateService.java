package com.example.fcb.play.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class StateService {

    private Logger logger = LoggerFactory.getLogger(StateService.class);

    public void saveSate(StateCommand command) {
        String uuid = command.getUuid();
        assert (command.getContent().equals(uuid));
        logger.info("uuid {}", command.getUuid());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        assert (command.getContent().equals(uuid));
        logger.info("uuid {}", command.getUuid());
    }
}
