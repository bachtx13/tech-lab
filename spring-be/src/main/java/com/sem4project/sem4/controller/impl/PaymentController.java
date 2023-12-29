package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.config.PaymentConfig;
import com.sem4project.sem4.dto.request.PaymentRequestDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.PaymentService;
import com.sem4project.sem4.util.HttpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Tag(name = "Payment", description = "Payment API")
@RestController
@RequestMapping(value = "/payment")
@PermitAll
@AllArgsConstructor
@CrossOrigin
public class PaymentController {
    private final PaymentService paymentService;


    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDto paymentRequestDto, HttpServletRequest request) throws UnsupportedEncodingException {
        paymentRequestDto.setIpAddress(HttpUtil.getRequestIP(request));
        String paymentUrl = paymentService.createPaymentUrl(paymentRequestDto);

        return ResponseEntity.ok(
                ResponseObject.builder()
                        .data(paymentUrl)
                        .build()
        );
    }
}

