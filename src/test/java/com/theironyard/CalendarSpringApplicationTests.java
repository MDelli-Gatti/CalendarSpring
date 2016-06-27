package com.theironyard;

import com.theironyard.services.EventRepository;
import com.theironyard.services.UserRepositroy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {


	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	UserRepositroy users;

	@Autowired
	EventRepository events;

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login").param("username", "Alice").param("password", "p")
		);

		Assert.assertTrue(users.count() == 1);
	}

	@Test
	public void testCreateEvent() throws Exception {
		//testLogin();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-event")
					.param("description", "This is my event")
					.param("time", LocalDateTime.now().toString())
					.sessionAttr("username", "Alice") //instead of testLogin()
		);

		Assert.assertTrue(events.count() == 1 );
	}
}
