package net.seitter.basicreq;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequirementManager {

	private static Logger LOG = LoggerFactory.getLogger(RequirementManager.class);
	
	private Repository repo;
	private Git git;
	
	public RequirementManager(String location) throws IOException {

		File repoFile = new File(location);
		if (!(repoFile.exists() && repoFile.isDirectory())) {
			
			repoFile.mkdirs();
			// Create a new repository
			Repository newlyCreatedRepo = FileRepositoryBuilder.create(new File(repoFile,".git"));
			newlyCreatedRepo.create();
			this.repo=newlyCreatedRepo;
		} else {
			// Open an existing repository
			Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File(repoFile,".git")).build();
			this.repo=existingRepo;
		}
		
		git = new Git(this.repo);
		
		
		LOG.debug("created RepositoryManager for location "+location);
	}
	
	
	public Module createModule(String moduleName) throws Exception {
	
		File workTree = repo.getWorkTree();	
		File moduleDir = new File(workTree,moduleName);
		if (!moduleDir.mkdir()) throw new Exception("Module already exists");
		
		// no good idea to use *
		git.add().addFilepattern("*").call();
		git.commit().setMessage("added module "+moduleName).call();
		LOG.debug("successfully created module "+moduleName);
		
		Module m = new Module();
		m.setModulePath(moduleDir);
		return null;
	}
	
	
	public RequirementDocument createRequirementDocument(String requirementDocName) throws Exception {
		
		File workTree = repo.getWorkTree();
		File requirementDocument = new File(workTree,requirementDocName);
		if(!requirementDocument.createNewFile()) throw new Exception("RequirementDocument already exists");
		
		git.add().addFilepattern("*").call();
		git.commit().setMessage("added requirement document "+requirementDocument).call();
		
		LOG.debug("successfully created requirement document "+requirementDocName);
		return null;
	}
	
	
	public void createBaseline(String baselineName) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, GitAPIException {
		
		git.branchCreate().setName(baselineName).call();
		
	}
}


