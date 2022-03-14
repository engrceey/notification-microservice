package com.reloadly.notificationservice.service;

import com.reloadly.notificationservice.dto.request.EmailDto;

public interface SendMailService {
    void sendEmail(EmailDto emailDto);
}
