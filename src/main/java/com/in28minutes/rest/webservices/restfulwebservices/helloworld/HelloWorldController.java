package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping(method=RequestMethod.GET, path="/hello-world" )
    public String helloWorld() {
    	return "Hello World";
    }
	
	@GetMapping( path="/hello-world2" )
    public String helloWorld2() {
    	return "Hello World2";
    }
	
	@GetMapping( path="/hello-world-bean" )
    public HelloWorldBean helloWorldBean() {
    	return new HelloWorldBean("Hello World3");
    }
	
	//hello-world/path-variable/in28minutes
	@GetMapping( path="/hello-world/path-variable/{name}" )
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
    	return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
