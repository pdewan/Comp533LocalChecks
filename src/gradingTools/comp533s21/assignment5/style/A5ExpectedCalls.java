package gradingTools.comp533s21.assignment5.style;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.AccessModifiersMatched;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedCallsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.VariableHasInterfaceTypeRatioCheck;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(20)
public class A5ExpectedCalls extends ExpectedCallsRatioCheck{
	 public A5ExpectedCalls() {
		 super();
	 }
	 
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        TestCaseResult aSuperResult = super.test(aProject, autoGrade);
			A5TaggedClassesDefined aTaggedClassesDefined = (A5TaggedClassesDefined) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A5TaggedClassesDefined.class);
			return aTaggedClassesDefined.computeResultBasedOnTaggedClasses(aSuperResult);
	        
	    }
	
	

}
