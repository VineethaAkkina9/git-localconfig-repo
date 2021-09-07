package com.example.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class LimitsConfigurationController {
	
	@Autowired
	Configuration configuration;
	
	@GetMapping("/limits")  
	public LimitConfiguration retriveLimitsFromConfigurations()  
	{  
	return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());  
	}  
	
	@GetMapping("/fault-tolerance-example")
	
	@HystrixCommand(fallbackMethod="fallbackRetreiveConfigurations")
	
	public LimitConfiguration retrieveConfigurations()
	{
		throw new RuntimeException("Not Availabe");
	}
	public LimitConfiguration fallbackRetreiveConfigurations() {
		
		return new LimitConfiguration(999, 9);   
	}
System.out.println("HI");
}
