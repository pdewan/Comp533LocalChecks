package gradingTools.comp533s21.assignment7.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(1)
public class A7NamedConstants extends NamedConstantsRatioCheck{
	 public A7NamedConstants() {
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
