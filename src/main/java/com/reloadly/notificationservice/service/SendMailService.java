package com.reloadly.notificationservice.service;

import com.reloadly.notificationservice.dto.request.EmailDto;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendEmail(EmailDto emailDto);
    void sendEmailWithAttachment(EmailDto emailDto) throws MessagingException;
}
