package gradingTools.comp533s21.assignment1.style;

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
import gradingTools.comp533s21.assignment1.A1ConfigurationProvided;
import gradingTools.comp533s21.assignment4.style.A4TaggedClassesDefined;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(20)
public class A1ExpectedCalls extends ExpectedCallsRatioCheck{
	 public A1ExpectedCalls() {
		 super();
	 }
	 @Override
	 protected Class configurationClass () {
		 return A1ConfigurationProvided.class;
	 }
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//	        TestCaseResult aSuperResult = super.test(aProject, autoGrade);
//	        A1ConfigurationProvided aConfigurationProvided = (A1ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A1ConfigurationProvided.class);
//			return aConfigurationProvided.computeResultBasedOnTaggedClasses(aSuperResult);
		return super.test(aProject, autoGrade);
	        
//	        return retVal;

	        
	    }
}
