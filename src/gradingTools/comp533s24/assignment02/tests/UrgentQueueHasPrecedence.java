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
@Explanation("Checks file <urp.out> to see if urgent queue gets precedence ove entry queue")
public class UrgentQueueHasPrecedence extends MonitorQueueTest{
	private String fileName = "uqp.out";
	int size = 4;
	protected String getFileName() {
		return fileName;
	}
	
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			String aFullFileName = getFullFileName(aProject);
			double aScore = LogGrader.testUrgentQueuePrecedence(aFullFileName);
			if (aScore == 1.0) {
				return pass();
			} else if (aScore == 0) {
				return fail("Did not have threads in entry queue when urgent queue attained maximum size");
			} else {
				return partialPass(aScore, "Urgent queue did not behave as expected");
			}
//			if (aResult) {
//				return pass();
//			}
//			else {
//				return fail("Could not show urgent queue has precedence");
//			}
//			int anEntryQueueSize = LogGrader.maxEntryQueueSize(aFullFileName);
//			int anUrgentQueueSize = LogGrader.maxUrgentQueueSize(aFullFileName);
//			if (anEntryQueueSize >= 4 && anUrgentQueueSize >= 4) {
//				return partialPass(0.9, "Entry queue of max size 4 was in fact FIFO");
//
//			}
			
//			if (aSize >= 4) {
//				return partialPass(0.9, "Entry queue of max size 4 was in fact FIFO");
//			} else {
//				return fail ("Max entry queue size < = 4, make it at least 4 to test FIFO prpperty");
//			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
