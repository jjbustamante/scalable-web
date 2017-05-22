package com.waes.scalable.web.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.waes.scalable.web.ScalableWebApp;
import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.data.IDiffRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScalableWebApp.class)
@WebAppConfiguration
public class DiffControlerIT {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    
    private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private IDiffRepository diffDAO;
	

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        diffDAO.deleteAll();
    }

    
	@Test
	public void testCreate() throws Exception {
		
		// Exercise the SUT
		this.mockMvc.perform(post("/v1/diff")
			.contentType(contentType))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(notNullValue())))
            .andExpect(jsonPath("$.file1", is(nullValue())))
            .andExpect(jsonPath("$.file2", is(nullValue())))
            .andExpect(jsonPath("$.differences", is(nullValue())))
            .andExpect(jsonPath("$.method", is(nullValue())));
	
	}
	
	@Test
	public void testGet() throws Exception {
		
		// Set up Fixture
		String id = setUpDiffFixture();
		
		// Exercise the SUT
		this.mockMvc.perform(get("/v1/diff/"+id)
			.contentType(contentType))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(notNullValue())))
            .andExpect(jsonPath("$.file1", is(nullValue())))
            .andExpect(jsonPath("$.file2", is(nullValue())))
            .andExpect(jsonPath("$.differences", is(nullValue())))
            .andExpect(jsonPath("$.method", is(nullValue())));
	}
	
	// TODO: I had problems testing the Multipart feature with MockMVC
	
	protected String setUpDiffFixture() {
		Diff diff = new Diff(Calendar.getInstance().getTime(), "API");
		diffDAO.save(diff);
		return String.valueOf(diff.getId());
	}
}
