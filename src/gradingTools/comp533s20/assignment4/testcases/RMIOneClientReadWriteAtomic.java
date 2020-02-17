package gradingTools.comp533s20.assignment4.testcases;

import java.util.Arrays;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s20.flexible.testcases.FlexibleOneClientCorrectReadWriteTestCase;
import gradingTools.comp533s19.flexible.testcases.FlexibleOneClientCorrectReadWriteTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.MaxValue;
import util.trace.Tracer;
@MaxValue(20)
public class RMIOneClientReadWriteAtomic extends FlexibleOneClientCorrectReadWriteTestCase {
	public RMIOneClientReadWriteAtomic() {
		super(false, false, true, false);
	}	
}
