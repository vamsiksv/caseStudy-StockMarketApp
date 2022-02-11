package com.example.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Company code is not valid.")
public class CompanyCodeNotExistException extends Exception
{
	/*
	 * public CompanyCodeExistsException(Throwable t){ super(t); }
	 */
	
}
