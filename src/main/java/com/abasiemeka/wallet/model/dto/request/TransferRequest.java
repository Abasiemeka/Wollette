package com.abasiemeka.wallet.model.dto.request;

import java.math.BigDecimal;

public record TransferRequest(String recipientEmail, BigDecimal amount) {
}
