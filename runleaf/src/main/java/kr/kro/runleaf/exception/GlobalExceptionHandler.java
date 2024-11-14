package kr.kro.runleaf.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();

		// 각 필드의 에러 메시지를 map에 담음
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			errors.put(error.getObjectName(), error.getDefaultMessage());
		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
