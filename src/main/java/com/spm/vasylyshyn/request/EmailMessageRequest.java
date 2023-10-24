package com.spm.vasylyshyn.request;

import lombok.Data;

@Data
public class EmailMessageRequest {
    private String subject;
    private String message;
}
