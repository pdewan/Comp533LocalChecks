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
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterTestInputGenerator;
//import grader.execution.ExecutionSpecificationSelector;
//import gradingTools.comp110.assignment1.testcases.PromptTestCase;
//import gradingTools.comp110.assignment4.Assignment4Requirements;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment3.testcases.GIPCRMINIOStaticArguments;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.shared.testcases.utils.ABufferingTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Comp533Tags;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
import util.trace.Tracer;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.testcase.PassFailJUnitTestCase;

@MaxValue(20)
//@Group("Test group name ")
public class ExplicitReceiveClientTestCase extends PassFailJUnitTestCase {
	
	
	protected SubstringSequenceChecker regularClient1Checker ;;	

	
	protected DistributedCounterProgramRunningTestCase explicitReceiveServerTestCase;
	String clientName;
	protected ABufferingTestInputGenerator outputBasedInputGenerator = new DistributedCounterTestInputGenerator();
	protected RunningProject interactiveInputProject;
	
	public ExplicitReceiveClientTestCase(
			DistributedCounterProgramRunningTestCase anExplicitReceiveServerTestCase,
			String aClientName) {
//		super("Explicit Receive Client Test Case");
		explicitReceiveServerTestCase = anExplicitReceiveServerTestCase;
		clientName = aClientName;


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
					interactiveInputProject.getProcessOutput().get(clientName);
//			ARegularCounterServerChecker aServerChecker = new ARegularCounterServerChecker(1.0);
			
			boolean aRetval = regularClient1Checker.check(aClient1Output);
			if (!aRetval) {
				return fail("Did not match:" + Arrays.toString(regularClient1Checker.getSubstrings()));
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
