package gradingTools.comp533s21.assignment2.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.comp533s21.assignment2.A2ConfigurationProvided;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(1)
public class A2NamedConstants extends NamedConstantsRatioCheck{
	 public A2NamedConstants() {
		 super();
	 }
	 
	 @Override
		protected Class configurationClass() {
			return A2ConfigurationProvided.class;
		}
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        return super.test(aProject, autoGrade);

	        
	    }
}
