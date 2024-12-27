package com.abasiemeka.wallet.model.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse (
		String error,
		String message,
		int status,
		long timestamp
) {}

