package com.example.fcb.state;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StateCommand extends BaseState{

    private String name;
    private String content;
}
