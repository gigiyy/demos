package com.example.demo.state;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StateCommand extends BaseState{

    private String name;
    private String content;
}
