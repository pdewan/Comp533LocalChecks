package gradingTools.comp533s20.assignment4.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s20.flexible.testcases.FlexibleOneClientCorrectConnectionTestCase;
import gradingTools.comp533s19.flexible.testcases.FlexibleOneClientCorrectConnectionTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class RMIOneClientConnection extends FlexibleOneClientCorrectConnectionTestCase {
	
	
	public RMIOneClientConnection() {
//		super("One client correct connection test case");

		super(false, true, false);

	}
	

}
