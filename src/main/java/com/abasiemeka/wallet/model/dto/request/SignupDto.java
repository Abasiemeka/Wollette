package com.abasiemeka.wallet.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Builder
public record SignupDto (
		@NotBlank(message = "First Name is required")
		String firstName,
		
		@NotBlank(message = "Middle Name is required")
		String middlename,
		
		@NotBlank(message = "Last Name is required")
		String lastName,
		
		@Size(max = 100)
		@NotBlank(message = "Email Name is required")
		@Email(message = "A valid email is required")
		@Column(unique = true)
		String email,
		
		@NotNull
		@NotBlank(message = "Password is required")
		@Size(max = 20, message = "Password must be less than or equal to 20 characters")
		@Size(min = 8, message = "Password must be more than or equal to 8 characters")
		@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+=]).{8,}$", message = "Password must contain at least 8 characters, one digit, one lowercase letter, one uppercase letter, and one special character")
		String password,
		
		@Size(max = 100)
		@NotBlank(message = "Email Name is required")
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@Column(unique = true)
		LocalDate dob,
		
		@NotNull
		@NotBlank(message = "Password is required")
		@Size(min = 11, max = 11, message = "Enter valid BVN")
		String bvn
) {}
