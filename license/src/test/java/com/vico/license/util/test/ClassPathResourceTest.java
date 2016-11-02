package com.vico.license.util.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vico.license.util.ClassPathResourceURI;

public class ClassPathResourceTest {
	private static final Logger LOGGER = Logger.getLogger(ClassPathResourceTest.class); 
	
	@Test
	public void testGetResourceURI() {
		//fail("Not yet implemented");
		LOGGER.info("testGetResourceURI"+ClassPathResourceURI.getResourceURI("/"));
		
	}

	@Test
	public void testGetWorkspacePath() {
		//fail("Not yet implemented");
		LOGGER.info("testGetWorkspacePath"+ClassPathResourceURI.getProjectPath());
	}

	@Test
	public void testGetProjectPath() {
		//fail("Not yet implemented");
		LOGGER.info("testGetResourceURI"+ClassPathResourceURI.getProjectPath());
	}

	@Test
	public void testGetParentFilePath() {
		//fail("Not yet implemented");
	}

}
