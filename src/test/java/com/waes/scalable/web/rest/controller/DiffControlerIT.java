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

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.waes.scalable.web.ScalableWebApp;
import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.data.IDiffRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScalableWebApp.class)
@WebAppConfiguration
public class DiffControlerIT {

	private static final String LEFT_METHOD = "LEFT";
	private static final String RIGTH_METHOD = "RIGHT";
	private static final String MESSAGE_NOT_EQUAL_SIZE = "Files are not equal size";
	private static final String MESSAGE_EQUAL = "Files are Equal";
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
	
	@Test
	public void testPostDiffLeftWithFilesNotEqualSize() throws Exception {
		verifyDiffOperation("some other", "some other type", LEFT_METHOD, MESSAGE_NOT_EQUAL_SIZE);
	}

	@Test
	public void testPostDiffLeftWithFilesEqual() throws Exception {
		verifyDiffOperation("some other", "some other", LEFT_METHOD, MESSAGE_EQUAL);
	}
	
	@Test
	public void testPostDiffRighttWithFilesNotEqualSize() throws Exception {
		verifyDiffOperation("some other", "some other type", RIGTH_METHOD, MESSAGE_NOT_EQUAL_SIZE);
	}

	@Test
	public void testPostDiffRightWithFilesEqual() throws Exception {
		verifyDiffOperation("some other", "some other", RIGTH_METHOD, MESSAGE_EQUAL);
	}

	protected String setUpDiffFixture() {
		Diff diff = new Diff(Calendar.getInstance().getTime(), "API");
		diffDAO.save(diff);
		return String.valueOf(diff.getId());
	}

	private void verifyDiffOperation(String data1, String data2, String method, String message) throws Exception {
		// Set up Fixture
		String id = setUpDiffFixture();
		MockMultipartFile file1 = createFile(0, data1);
		MockMultipartFile file2 = createFile(1, data2);

		// Excercise SUT
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/diff/"+id+"/"+method.toLowerCase())
				.file(file1)
				.file(file2)
				.contentType("multipart/mixed"))
				.andExpect(status().isOk());

		// Verify
		verifyDiffResult(id, message, method);
	}

	private void verifyDiffResult(String id, String result, String method ) throws Exception {
		this.mockMvc.perform(get("/v1/diff/"+id)
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.file1", is(RIGTH_METHOD.equals(method) ? "test1.txt" : "test0.txt")))
				.andExpect(jsonPath("$.file2", is(RIGTH_METHOD.equals(method) ? "test0.txt" : "test1.txt")))
				.andExpect(jsonPath("$.differences", Matchers.contains(result)))
				.andExpect(jsonPath("$.method", is(method)));
	}

	private static MockMultipartFile createFile(int i, String data) {
		return new MockMultipartFile("files["+i+"]", "test"+i+".txt",
				"text/plain", data.getBytes());
	}
}
