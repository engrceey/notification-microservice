package com.reloadly.notificationservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {
    private String subject;
    private String recipient;
    private String body;
    private String attachment;
    private String cc;
    private String bcc;
    private String sender;
}