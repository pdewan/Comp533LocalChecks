package gradingTools.comp533s21.assignment4.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(1)
@IsExtra(true)
public class A4NamedConstants extends NamedConstantsRatioCheck{
	 public A4NamedConstants() {
		 super();
	 }
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        return super.test(aProject, autoGrade);

	        
	    }
}
