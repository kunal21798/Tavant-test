package com.tavant.test.controlleradvice;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.test.errorresponse.ErrorResponse;
import com.tavant.test.exception.AccountNotFoundException;
import com.tavant.test.exception.NoDataFoundException;

@ControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(NoDataFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNoDataFoundFoundException(
			NoDataFoundException e,WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT REQUEST",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleAccountNotFoundException(
			AccountNotFoundException e,WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse = new ErrorResponse("CANNOT FIND DATA",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
}


