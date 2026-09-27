package com.example.foodordering.FoodOrderingAPI.dto.responseDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class PaymentResponse {
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime paymentDateTime;
    private String paymentMethod;
    private String status;
}
