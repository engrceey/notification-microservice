package com.reloadly.notificationservice.controller;

import com.reloadly.notificationservice.dto.request.EmailDto;
import com.reloadly.notificationservice.dto.response.ApiResponse;
import com.reloadly.notificationservice.service.SendMailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("mail")
public class SendMailController {

    private final SendMailService sendMailService;


    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> sendMail(@RequestBody @Valid final EmailDto emailDto) {
        sendMailService.sendEmail(emailDto);
        return ResponseEntity.ok(ApiResponse.<Boolean>builder()
                .isSuccessful(true)
                .statusMessage("email sent successfully")
                .data(true)
                .build()
        );
    }
}
