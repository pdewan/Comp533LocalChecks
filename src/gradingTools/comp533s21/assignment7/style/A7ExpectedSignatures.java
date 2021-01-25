package gradingTools.comp533s21.assignment7.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.AccessModifiersMatched;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedCallsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.ExpectedSignaturesRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.VariableHasInterfaceTypeRatioCheck;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(5)
public class A7ExpectedSignatures extends ExpectedSignaturesRatioCheck{
	 public A7ExpectedSignatures() {
		 super();
	 }
	
	 protected Class taggedClassesDefined() {
			return A7TaggedClassesDefined.class;
		}
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        
			TestCaseResult aSuperResult = super.test(aProject, autoGrade);
			return aSuperResult;
//			A7TaggedClassesDefined aTaggedClassesDefined = (A7TaggedClassesDefined) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A7TaggedClassesDefined.class);
//			return aTaggedClassesDefined.computeResultBasedOnTaggedClasses(aSuperResult);
	        
	    }
}
