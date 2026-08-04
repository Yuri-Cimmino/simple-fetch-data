package it.eng.spring.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException manve){
		Errore manveErr = new Errore("VAL-ERR-01", manve.getBindingResult().getFieldError().getDefaultMessage());
		return ResponseEntity.badRequest().body(manveErr);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleBusinessException(BusinessException be){
		Errore err = new Errore("ERR-1","Error BusinessException: " + be);
		return ResponseEntity.badRequest().body(err);
	}

}
