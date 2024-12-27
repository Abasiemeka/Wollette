package com.abasiemeka.wallet.model.dto.response;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
public final class GenericResponse {
	
	private final String message;
	private final ResponseStatus status;
	private final Object data;
	
	public GenericResponse(String message, ResponseStatus status, @Nullable Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public enum ResponseStatus {
		SUCCESSFUL, FAILED, ERROR, UNAUTHORIZED, NOT_FOUND, BAD_REQUEST, FORBIDDEN, CONFLICT;
	}
	
	public static GenericResponse successful(String message) {
		return successful(message, null);
	}
	
	public static GenericResponse successful(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.SUCCESSFUL)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse error(String message) {
		return error(message, null);
	}
	
	public static GenericResponse error(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.ERROR)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse successful() {
		return successful("Operation successful");
	}
	
	public static GenericResponse error() {
		return error("An error occurred");
	}
	
	private static void validateMessage(String message) {
		if (message == null || message.trim().isEmpty()) {
			throw new IllegalArgumentException("Message cannot be null or empty");
		}
	}
	
	@Override
	public String toString() {
		return "GenericResponse{" +
				"message='" + message + '\'' +
				", status=" + status +
				", data=" + data +
				'}';
	}
	
	public static GenericResponse failed(String message) {
		return failed(message, null);
	}
	
	public static GenericResponse failed(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.FAILED)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse unauthorized(String message) {
		return unauthorized(message, null);
	}
	
	public static GenericResponse unauthorized(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.UNAUTHORIZED)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse notFound(String message) {
		return notFound(message, null);
	}
	
	public static GenericResponse notFound(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.NOT_FOUND)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse badRequest(String message) {
		return badRequest(message, null);
	}
	
	public static GenericResponse badRequest(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.BAD_REQUEST)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse forbidden(String message) {
		return forbidden(message, null);
	}
	
	public static GenericResponse forbidden(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.FORBIDDEN)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public static GenericResponse conflict(String message) {
		return conflict(message, null);
	}
	
	public static GenericResponse conflict(String message, Object data) {
		validateMessage(message);
		return GenericResponse.builder()
				.message(message)
				.status(ResponseStatus.CONFLICT)
				.data(Optional.ofNullable(data))
				.build();
	}
	
	public String message() {
		return message;
	}
	
	public ResponseStatus status() {
		return status;
	}
	
	public Object data() {
		return data;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (GenericResponse) obj;
		return Objects.equals(this.message, that.message) &&
				Objects.equals(this.status, that.status) &&
				Objects.equals(this.data, that.data);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(message, status, data);
	}
}
