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
@Explanation("Checks file <tm.out> to see if client thread mapping changes")
public class ThreadMappingChanges extends MonitorQueueTest{
	private String fileName = "tm.out";
	protected String getFileName() {
		return fileName;
	}
	
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			String aFullFileName = getFullFileName(aProject);
			double aScore = LogGrader.testThreadAssignments(aFullFileName);
			if (aScore == 1.0) {
				return pass();
			} else if (aScore == 0) {
				return fail("Reassignment did not occur");
			} else {
				return partialPass(aScore, "Reassignment did not occur despite recommended operatons");
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
