package com.fjw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {MyIllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlerIllegalArgumentException(MyIllegalArgumentException e) {
		System.out.println(e.getMessage());
		return e.getMessage();
	}
	
	@ExceptionHandler(value = {IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlerIllegalArgumentException(IllegalStateException e) {
		System.out.println(e.getMessage());
		return e.getMessage();
	}
	
	@ExceptionHandler(value = {BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlerValidException(BindException e) {
		System.out.println(e.getMessage());
		return e.getMessage();
	}
	
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handerException(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		return e.getMessage();
	}
}
