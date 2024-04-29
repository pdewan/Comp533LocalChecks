package gradingTools.comp533s24.assignment02.tests;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s24.assignment02.LogGrader;
import gradingTools.comp533s24.assignment02.MonitorQueueTest;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Checks file <uq4.out> to see if entry queue ever had at least 4 elements")
public class EntryQueueHas4 extends MonitorQueueTest{
	private String fileName = "eq4.out";
	int size = 4;
	protected String getFileName() {
		return fileName;
	}
	
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			String aFullFileName = getFullFileName(aProject);
			int aSize = LogGrader.maxEntryQueueSize(aFullFileName);
			boolean aResult = aSize >= size;
			double score = ((double) aSize)/size;
			
			if (aResult) {
				return pass();
			} else {
				return partialPass(score, "Max entry queue size " + aSize + " instead of " + size);
			}			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
