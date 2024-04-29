package gradingTools.comp533s24.assignment02;

import java.io.File;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;

public class FileTest extends PassFailJUnitTestCase{
	protected String fileName = null;
	public String getFileName() {
		return fileName;
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			File aProjectFolder = project.getProjectFolder();			
			if (aProjectFolder.isDirectory()) {
				File[] aFiles = aProjectFolder.listFiles();
				for (File aFile:aFiles) {
					if (aFile.getName().endsWith(".out")) {
							fileName = aFile.getCanonicalPath();
							return pass();
					}
				}
			}
			return fail("No file ending with .out found in " + aProjectFolder.getCanonicalPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
