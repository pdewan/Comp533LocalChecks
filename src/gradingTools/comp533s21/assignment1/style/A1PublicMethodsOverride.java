package gradingTools.comp533s21.assignment1.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(10)
@IsExtra(true)
public class A1PublicMethodsOverride extends PublicMethodsOverrideRatioCheck{
	 public A1PublicMethodsOverride() {
		 super();
	 }
	 @Override
		protected Class configurationClass() {
			return A1ConfigurationProvided.class;
		}

	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        return super.test(aProject, autoGrade);

	        
	    }
}
