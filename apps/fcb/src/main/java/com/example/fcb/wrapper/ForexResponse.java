package com.example.fcb.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForexResponse implements ResponseBody {
    private String id;
    private String result;
}
