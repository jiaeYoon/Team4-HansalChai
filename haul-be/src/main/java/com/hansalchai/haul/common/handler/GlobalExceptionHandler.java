package com.hansalchai.haul.common.handler;

import static org.springframework.http.HttpStatus.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hansalchai.haul.common.exceptions.BadRequestException;
import com.hansalchai.haul.common.exceptions.ConflictException;
import com.hansalchai.haul.common.exceptions.ForbiddenException;
import com.hansalchai.haul.common.exceptions.InternalServerError;
import com.hansalchai.haul.common.exceptions.NotFoundException;
import com.hansalchai.haul.common.exceptions.UnauthorizedException;
import com.hansalchai.haul.common.utils.ApiResponse;
import com.hansalchai.haul.common.utils.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
		HttpServletRequest request) {
		exception.printStackTrace();
		ErrorCode errorCode = ErrorCode.INVALID_DATA;
		logInfo(request, BAD_REQUEST, errorCode.getMessage());
		return ApiResponse.error(errorCode);
	}

	@ExceptionHandler(BadRequestException.class)
	protected ApiResponse<Object> handleBadRequestException(BadRequestException exception, HttpServletRequest request) {
		logInfo(request, exception.getCode().getStatus(), exception.getCode().getMessage());
		return ApiResponse.error(exception.getCode());
	}

	//401 Unauthorized
	@ExceptionHandler(UnauthorizedException.class)
	protected ApiResponse<Object> handleUnauthorizedException(
		UnauthorizedException exception,
		HttpServletRequest request) {
		logInfo(request, UNAUTHORIZED, exception.getMessage());
		return ApiResponse.error(exception.getCode());
	}

	//403 Forbidden
	@ExceptionHandler(ForbiddenException.class)
	protected ApiResponse<Object> handleForbiddenException(
		ForbiddenException exception,
		HttpServletRequest request) {
		logInfo(request, FORBIDDEN, exception.getMessage());
		return ApiResponse.error(exception.getCode());
	}

	//404 Not Found
	@ExceptionHandler(NotFoundException.class)
	protected ApiResponse<Object> handleNotFoundException(
		NotFoundException exception,
		HttpServletRequest request) {
		logInfo(request, NOT_FOUND, exception.getMessage());
		return ApiResponse.error(exception.getCode());
	}

	// 409 Conflict
	@ExceptionHandler(ConflictException.class)
	protected ApiResponse<Object> handleConflictException(
		ConflictException exception,
		HttpServletRequest request) {
		logInfo(request, CONFLICT, exception.getMessage());
		return ApiResponse.error(exception.getCode());
	}

	// 500 Internal Error
	@ExceptionHandler(InternalServerError.class)
	protected ApiResponse<Object> handleInternalServerError(
		InternalServerError exception,
		HttpServletRequest request) {
		logInfo(request, INTERNAL_SERVER_ERROR, exception.getMessage());
		return ApiResponse.error(exception.getCode());
	}

	private void logInfo(HttpServletRequest request, HttpStatus status, String message) {
		String logMessage = String.format("%s %s : %s - %s", request.getMethod(), request.getRequestURI(), status,
			message);
		logger.info(logMessage);
	}
}
