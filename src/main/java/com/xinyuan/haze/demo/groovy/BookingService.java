package com.xinyuan.haze.demo.groovy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingService {

	@Autowired(required=false)
	private Messenger messenger;
	
	public String booking() {
		return messenger.getMessage();
	}
}
