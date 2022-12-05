package com.example.demo.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateRequest {
    private String uuid;
    private String name;
    private String content;

    public StateCommand toCommand() {
        StateCommand command = new StateCommand();
        command.setName(name);
        command.setUuid(uuid);
        command.setContent(content);

        return command;
    }
}
