package gradingTools.comp533s19.assignment1.testcases;

import java.util.Set;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;

public class SingleClassTaggedTestCase extends PassFailJUnitTestCase {
	private final String tag;
	
	
	public SingleClassTaggedTestCase(String tag) {
//		super("Prompt printer test case");
//		super("Single class tagged '" + tag + "' test case");
		this.tag = tag;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		Set<ClassDescription> aClasses = BasicProjectIntrospection.findClassesByTag(project, tag);
    	if (aClasses.size() == 1) {
    		return pass();
    	}
    	if (aClasses.size() > 1) {
    		return partialPass(0.5, "Multiple classes tagged:" + tag + " " + aClasses);
    	}
    	return fail("No binary class tagged: " + tag);
	}
}
