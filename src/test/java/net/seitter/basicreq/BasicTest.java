package net.seitter.basicreq;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

public class BasicTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		RequirementManager rm = new RequirementManager("./test");
		
		rm.createModule("testModule");
		
		rm.createRequirementDocument("testReq1");

		rm.createModule("testModule2");

		rm.createBaseline("baseline1");
		
		rm.createRequirementDocument("testReq2");
		
		rm.createBaseline("baseline2");

		
	}

}
