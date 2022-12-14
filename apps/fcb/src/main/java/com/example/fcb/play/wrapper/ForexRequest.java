package com.example.fcb.play.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForexRequest implements RequestBody<ForexRequest> {

    private String id;
    private String message;
}
