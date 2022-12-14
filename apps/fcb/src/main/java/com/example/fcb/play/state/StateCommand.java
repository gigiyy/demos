package com.example.fcb.play.state;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
class StateCommand extends BaseState {

    private String name;
    private String content;
}
