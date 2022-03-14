package com.reloadly.notificationservice.service;

import com.reloadly.notificationservice.dto.request.EmailDto;
import com.reloadly.notificationservice.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService{
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(EmailDto emailDto) {

        log.info("inside Send email, building mail!!");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(emailDto.getSender());
            mimeMessageHelper.setTo(emailDto.getRecipient());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody());
        };

        try {
            mailSender.send(mimeMessagePreparator);
            log.info("email has sent!!");
        }catch (MailException exception) {
            log.error("Exception occurred when sending mail {}",exception.getMessage());
            throw new CustomException("Exception occurred when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    public void sendEmailWithAttachment(EmailDto emailDto) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("noreply@gmail.com");
        mimeMessageHelper.setTo(emailDto.getRecipient());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(emailDto.getBody());

        try {
            FileSystemResource fileSystemResource =
                    new FileSystemResource(new File(emailDto.getAttachment()));

            mimeMessageHelper
                    .addAttachment(Objects
                            .requireNonNull(fileSystemResource.getFilename()),fileSystemResource);

            mailSender.send(mimeMessage);
        } catch (MailException exception) {
            log.error("Exception occurred when sending mail {}",exception.getMessage());
            throw new CustomException("Exception occurred when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
