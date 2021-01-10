package gradingTools.comp533s21.assignment6.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.AccessModifiersMatched;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedCallsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.SpuriousCallsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.VariableHasInterfaceTypeRatioCheck;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(20)
public class A6SpuriousCalls extends SpuriousCallsRatioCheck{
	 public A6SpuriousCalls() {
		 super();
	 }
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        return super.test(aProject, autoGrade);

	        
	    }
}
