package gradingTools.comp533s24.assignment02.tests;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s24.assignment02.grader.LogGrader;
import gradingTools.comp533s24.assignment02.grader.MonitorQueueTest;
import gradingTools.comp533s24.assignment02.grader.Orders;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Checks file <cqe.out> to see if the ccondition queue was ever entered")
public class ConditionQueueEntered extends MonitorQueueTest{
	private String fileName = "cqe.out";
	protected String getFileName() {
		return fileName;
	}
	
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			String aFullFileName = getFullFileName(aProject);
			Orders orders = Orders.extraOrdersFromFile(aFullFileName);
			boolean aResult = LogGrader.testConditionQueueHasSize(aFullFileName, 1);
			if (aResult) {
				return pass();
			} else {
				return fail("Condition queue never had at least one entry");
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return fail(e.getMessage());
		}
	}

}
