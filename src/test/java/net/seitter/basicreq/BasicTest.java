package net.seitter.basicreq;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class BasicTest {

	public static void main(String[] args) throws Exception {
		RequirementManager rm = new RequirementManager("./test");

		rm.createModule("testModule");

		rm.createRequirementDocument("testReq1");

		rm.createModule("testModule2");

		rm.createBaseline("baseline1");

		rm.createRequirementDocument("testReq2");

		rm.createBaseline("baseline2");
	}

	
	
	@Test
	public void testSomeLibraryMethod() {
		try {
			RequirementManager classUnderTest = new RequirementManager("/tmp/test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
	}
}
