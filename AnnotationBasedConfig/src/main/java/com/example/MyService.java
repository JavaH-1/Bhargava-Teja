package com.example;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	 private String message = "Hello from Annotations!";

	    public void displayMessage() {  // ✅ This method is defined here
	        System.out.println(message);
	    }
}
