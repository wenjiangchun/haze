package com.xinyuan.haze.demo.groovy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.demo.groovy.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class BookingServiceTest {

	@Autowired 
	private BookingService bookingService;
	
	@Test
	public void testSave() {
		System.out.println(bookingService.booking());
	}
	
}
