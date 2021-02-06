package com.tavant.test.errorresponse;

import java.io.Serializable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.tavant.test.errorresponse.ApiSubError;
import com.tavant.test.errorresponse.ApiValidationError;

import lombok.Data;

@Data
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.CUSTOM,
//property = "error",visible = true)
//@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class Apierror implements Serializable {
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private HttpStatus status;
		    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
		    private LocalDateTime timestamp;
		    private String message;
		    private String debugMessage;
		    private List<ApiSubError> subErrors;

		    private Apierror() {
		        timestamp = LocalDateTime.now();
		    }

		    public Apierror(HttpStatus status) {
		        this();
		        this.status = status;
		    }

		    public Apierror(HttpStatus status, Throwable ex) {
		        this();
		        this.status = status;
		        this.message = "Unexpected error";
		        this.debugMessage = ex.getLocalizedMessage();
		    }

		    public Apierror(HttpStatus status, String message, Throwable ex) {
		        this();
		        this.status = status;
		        this.message = message;
		        this.debugMessage = ex.getLocalizedMessage();
		    }

		  //adding into array list
		    private void addSubError(ApiSubError subError) {
		        if (subErrors == null) {
		            subErrors = new ArrayList<>();
		        }
		        subErrors.add(subError);
		    }

		  //helps in taking all validation details and adds to the list(suberror list)
		    private void addValidationError(String object, String field, Object rejectedValue, String message) {
		        addSubError(new ApiValidationError(object, field, rejectedValue, message));
		    }

		    private void addValidationError(String object, String message) {
		        addSubError(new ApiValidationError(object, message));
		    }

		  //to read the errors that are coming for the validation annotations

		    public void addValidationError(FieldError fieldError) {
		        this.addValidationError(
		                fieldError.getObjectName(),
		                fieldError.getField(),
		                fieldError.getRejectedValue(),
		                fieldError.getDefaultMessage());
		    }

		    public void addValidationErrors(List<FieldError> fieldErrors) {
		        fieldErrors.forEach(this::addValidationError);
		    }

		    private void addValidationError(ObjectError objectError) {
		        this.addValidationError(
		                objectError.getObjectName(),
		                objectError.getDefaultMessage());
		    }

		    public void addValidationError(List<ObjectError> globalErrors) {
		        globalErrors.forEach(this::addValidationError);
		    }

		    /**
		     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
		     *
		     * @param cv the ConstraintViolation
		     */
		  //to utilize @Valid annotations
		    private void addValidationError(ConstraintViolation<?> cv) {
		        this.addValidationError(
		                cv.getRootBeanClass().getSimpleName(),
		                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
		                cv.getInvalidValue(),
		                cv.getMessage());
		    }

		    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		        constraintViolations.forEach(this::addValidationError);
		    }


		
	}

}
