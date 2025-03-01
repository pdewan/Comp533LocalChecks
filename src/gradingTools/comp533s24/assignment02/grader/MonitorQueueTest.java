package gradingTools.comp533s24.assignment02.grader;

import java.io.File;
import java.io.IOException;

import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;

public abstract class MonitorQueueTest extends PassFailJUnitTestCase{
	private String fileName = null;
	private String folderName =  "queueLogs";
	protected abstract String getFileName() ;
	protected String getFullFileName(Project aProject) throws IOException {
		File aFile = new File(aProject.getProjectFolder(), folderName + "/" + getFileName( ));
		if (!aFile.exists()) {
			int i = 1;
		}
		return aFile.getAbsolutePath();
//		return aProject.getProjectFolder().getCanonicalPath()  + "/" + folderName + "/" + getFileName();
	}
	
}
