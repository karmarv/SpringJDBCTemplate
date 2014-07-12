
package com.rhl.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
									"/Spring-Module.xml" })
public class UtilityControllerTest {

	
	protected static Logger log = LoggerFactory.getLogger(UtilityControllerTest.class);

	@Autowired
	protected ApplicationContext applicationContext;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("http.proxyHost", "web-proxy.ind.hp.com");  
		System.setProperty("http.proxyPort", "8080");	
		log.info("------------ START OF TEST ------------" );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("------------ END OF TEST ------------" );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Ignore
	@Test
	public void addData() throws JsonGenerationException, JsonMappingException, IOException {
	
		String postBodyContent = "{\"userId\":0,\"username\":\"R\",\"firstName\":\"A\",\"lastName\":\"B\",\"email\":\"@\",\"password\":\"X\",\"organizationId\":\"YYY\",\"phone\":\"666\",\"enabled\":1,\"createdTime\":null}";
		
		String url = "http://localhost:8080/RhlJDBCTemplate/users/";
		try {
			StringBuilder output = HttpUtility.httpPostRequest(url, postBodyContent);
			if(output != null){
				log.info(output.toString());
			}else{
				log.info("output is null");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	@Ignore
	@Test
	public void updateData() throws JsonGenerationException, JsonMappingException, IOException {
		String postBodyContent = "";
		String url = "http://localhost:8080/RhlJDBCTemplate/test/1";
		try {
			StringBuilder output = HttpUtility.httpputRequest(url, postBodyContent);
			if(output != null){
				log.info(output.toString());
			}else{
				log.info("output is null");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
}
