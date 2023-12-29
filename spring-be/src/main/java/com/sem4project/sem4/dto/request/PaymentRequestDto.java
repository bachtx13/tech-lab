package com.sem4project.sem4.dto.request;

import com.sem4project.sem4.util.HttpUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class PaymentRequestDto {
    String locate;
    String orderInfo;
    String ipAddress;
    String returnUrl;
    Long costAmount;
}
