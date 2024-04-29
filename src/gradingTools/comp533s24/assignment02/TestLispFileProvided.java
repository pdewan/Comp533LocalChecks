package gradingTools.comp533s24.assignment02;

import java.io.File;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;

public class TestLispFileProvided extends PassFailJUnitTestCase{
	protected String lispFileName = null;
	public String getLispFileName() {
		return lispFileName;
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			File aProjectFolder = project.getProjectFolder();
			if (aProjectFolder.isDirectory()) {
				File[] aFiles = aProjectFolder.listFiles();
				for (File aFile:aFiles) {
					if (aFile.getName().endsWith(".lisp")) {
							lispFileName = aFile.getCanonicalPath();
							return pass();
					}
				}
			}
			return fail("No file ending with .lisp found in " + aProjectFolder.getCanonicalPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
