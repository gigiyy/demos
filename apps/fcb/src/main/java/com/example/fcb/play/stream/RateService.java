package com.example.fcb.play.stream;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
class Message {

    int status;
    String name;
}

@Service
public class RateService {

    public List<Message> updateMessage(List<Message> messages) {
        return messages.stream()
            .filter((m) -> m.getStatus() == 1)
            .peek(m -> m.setStatus(2))
            .collect(Collectors.toList());
    }
}
