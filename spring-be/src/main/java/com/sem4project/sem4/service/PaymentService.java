package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.TransactionalDto;
import com.sem4project.sem4.dto.request.PaymentRequestDto;
import com.sem4project.sem4.entity.Transactional;

public interface PaymentService extends BaseService<Transactional, TransactionalDto> {
    String createPaymentUrl(PaymentRequestDto paymentRequestDto);
}
