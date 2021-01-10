package gradingTools.comp533s21.assignment6.style;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.basics.sharedTestCase.checkstyle.NamedConstantsRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.PublicMethodsOverrideRatioCheck;
import gradingTools.basics.sharedTestCase.checkstyle.VariableHasInterfaceTypeRatioCheck;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(10)
public class A6InterfaceAsType extends VariableHasInterfaceTypeRatioCheck{
	 public A6InterfaceAsType() {
		 super();
	 }
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        return super.test(aProject, autoGrade);

	        
	    }
}
