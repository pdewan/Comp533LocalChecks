package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import org.junit.Test;

//import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterTestInputGenerator;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
//import grader.execution.ExecutionSpecificationSelector;
//import gradingTools.comp110.assignment1.testcases.PromptTestCase;
//import gradingTools.comp110.assignment4.Assignment4Requirements;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment3.testcases.GIPCRMINIOStaticArguments;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.Tracer;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.testcase.JUnitTestCase;
import grader.basics.testcase.PassFailJUnitTestCase;

@MaxValue(20)
//@Group("Test group name ")
public class ExplicitReceiveServerTestCase extends PassFailJUnitTestCase {
	
	
	protected SubstringSequenceChecker checker = new ARegularCounterServerChecker();	

	
	protected JUnitTestCase explicitReceiveServerTestCase;
	protected ABufferingTestInputGenerator outputBasedInputGenerator = new DistributedCounterTestInputGenerator();
	protected RunningProject interactiveInputProject;
	protected String serverName;
	
	public ExplicitReceiveServerTestCase(String aName, String aServerName,
			JUnitTestCase anExplicitReceiveServerTestCase) {
//		super(aName);
		serverName = aServerName;
		explicitReceiveServerTestCase = anExplicitReceiveServerTestCase;

	}

	
	


	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
	
		try {
			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//			ExclicitReceiveTestInputGenerator anOutputBasedInputGenerator = 
//					new ExclicitReceiveTestInputGenerator();
			outputBasedInputGenerator = explicitReceiveServerTestCase.getOutputBasedInputGenerator();
			 interactiveInputProject = explicitReceiveServerTestCase.getInteractiveInputProject();

			if (outputBasedInputGenerator == null || interactiveInputProject == null) {
				return fail("Problem running explicit server test case");
			}
//			
			StringBuffer aClient1Output = 
					interactiveInputProject.getProcessOutput().get(serverName);
//			ARegularCounterServerChecker aServerChecker = new ARegularCounterServerChecker(1.0);
			
			boolean aRetval = checker.check(aClient1Output);
			if (!aRetval) {
				return fail("Did not match:" + Arrays.toString(checker.getSubstrings()));
			}
//			if (interactiveInputProject != null) {
//				interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
//			}
//			
//			if (!anOutputBasedInputGenerator.isNonsenseSetupComplete()) {
//			
//				return partialPass(0.80, "No nonsense");
//			}
//			
			return pass();
			
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
	
	
}
