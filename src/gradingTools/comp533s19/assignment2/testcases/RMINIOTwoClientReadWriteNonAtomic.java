package gradingTools.comp533s19.assignment2.testcases;

import java.util.Arrays;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s19.flexible.testcases.FlexibleTwoClientCorrectReadWriteTestCase;
import gradingTools.comp533s19.flexible.testcases.FlexibleTwoClientCorrectReadWriteTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class RMINIOTwoClientReadWriteNonAtomic extends FlexibleTwoClientCorrectReadWriteTestCase {

	public RMINIOTwoClientReadWriteNonAtomic() {
		super(false, true, true, false);
	}
	
}
