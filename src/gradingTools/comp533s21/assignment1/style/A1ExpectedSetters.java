package gradingTools.comp533s21.assignment1.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.MethodAccessModifierRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedCallsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedGettersRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedSettersRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedSignaturesRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.VariableHasClassTypeRatioCheck;
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(3)
public class A1ExpectedSetters extends ExpectedSettersRatioCheck{
	 public A1ExpectedSetters() {
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
