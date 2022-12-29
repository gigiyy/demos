package com.example.fcb.play.state;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
class StateService {

    public void saveSate(StateCommand command) {
        String first = command.getUuid();
        assert command.getContent().equals(first);
        log.info("uuid {}", command.getUuid());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        // java assert is disabled by default while running in production
        assert command.getContent().equals(first);
        String second = command.getUuid();
        // use Spring assert utils for validation purposes.
        Assert.isTrue(first.equals(second), "first and second uuid should be same");
        log.info("uuid {}", command.getUuid());
    }
}
