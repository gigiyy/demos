package com.example.fcb.play.number;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NumberRepositoryTest {
    Logger logger = LoggerFactory.getLogger(NumberRepositoryTest.class);

    @Autowired
    NumberRepository subject;

    @Autowired
    AnotherRepository anotherRepository;

    @Test
//    @Transactional
    // with @Transactional annotation, the found aEntity.numberA would be 55.243 instead of 55.24
    public void testSaveNumbers() throws JsonProcessingException {
        NumbersEntity entity = NumbersEntity.builder()
                .numberA(new BigDecimal("55.243"))
                .numberB(new BigDecimal("23.0"))
                .build();

        NumbersEntity saved = subject.save(entity);
        subject.flush();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(saved);

        logger.info("json {}", json);
        assertThatJson(json).inPath("$.numberA").isEqualTo("55.243");
        assertThatJson(json).inPath("$.numberB").isEqualTo("23.00");

        Optional<NumbersEntity> found = subject.findById(saved.id);
        found.ifPresent(aEntity -> {
            try {
                String aJson = mapper.writeValueAsString(aEntity);
                logger.info("found Json {}", aJson);
                assertThatJson(aJson).inPath("$.numberA").isEqualTo("55.24");
                assertThatJson(aJson).inPath("$.numberB").isEqualTo("23.00");

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        logger.info("saved {}", saved);
    }

    @Test
    public void testSequence() {
        anotherRepository.save(AnotherEntity.builder().name("someone").birthday(LocalDate.now()).build());
    }
}