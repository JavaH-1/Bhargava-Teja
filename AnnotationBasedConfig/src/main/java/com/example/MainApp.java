package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		 ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 MyService myService = context.getBean(MyService.class); // Get bean from context
	     myService.displayMessage();  //Call method (which exists in MyService)

	}

}

//
// The MyService class has the displayMessage() method.
// @Service tells Spring to manage this class as a bean.
// @ComponentScan finds and registers the bean automatically.
// context.getBean(MyService.class) gets the instance from Spring.
// Since displayMessage() exists in MyService, we can call it.