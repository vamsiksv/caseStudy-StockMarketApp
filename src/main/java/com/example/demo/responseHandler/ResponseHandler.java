package com.example.demo.responseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> handleResponse(String info , HttpStatus status,Object responseObject)
	{
		Map<String,Object> responseMap = new HashMap<String,Object>();
		responseMap.put("info", info);
		responseMap.put("status", status);
		responseMap.put("result", responseObject);
		
		return new ResponseEntity<Object>(responseMap,status);
		
	}

}
