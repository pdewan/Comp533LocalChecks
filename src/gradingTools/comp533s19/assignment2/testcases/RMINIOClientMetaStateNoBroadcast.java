package gradingTools.comp533s19.assignment2.testcases;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment2.Assignment2TwoClientSuite;
import gradingTools.comp533s19.flexible.testcases.FlexibleMetaStateBroadcastTestCase;
import gradingTools.comp533s19.flexible.testcases.MetaStateBroadcastTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.Group;
import util.annotations.MaxValue;
import util.trace.Tracer;

@MaxValue(20)
//@Group("Test group name")
public class RMINIOClientMetaStateNoBroadcast extends FlexibleMetaStateBroadcastTestCase {

	public RMINIOClientMetaStateNoBroadcast() {
		super(false, true, true, true, false);
	}
	
	
}
