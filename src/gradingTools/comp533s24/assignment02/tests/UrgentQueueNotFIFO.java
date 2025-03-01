package gradingTools.comp533s24.assignment02.tests;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s24.assignment02.grader.LogGrader;
import gradingTools.comp533s24.assignment02.grader.MonitorQueueTest;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Checks file <uqf.out> to see if urgent queue is not emptied in FIFO order")
public class UrgentQueueNotFIFO extends MonitorQueueTest{
	private String fileName = "uqf.out";
	int size = 4;
	protected String getFileName() {
		return fileName;
	}
	
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			String aFullFileName = getFullFileName(aProject);
			boolean aResult = LogGrader.testThatUrgentQueueIsNotAQueue(aFullFileName);
			if (aResult) {
				return pass();
			}
			int aSize = LogGrader.maxEntryQueueSize(aFullFileName);
			if (aSize >= 4) {
				return partialPass(0.9, "Entry queue of max size 4 was in fact FIFO");
			} else {
				return fail ("Max entry queue size < = 4, make it at least 4 to test FIFO prpperty");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
