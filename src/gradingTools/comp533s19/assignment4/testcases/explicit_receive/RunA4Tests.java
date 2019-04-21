package gradingTools.comp533s19.assignment4.testcases.explicit_receive;

import gradingTools.comp533s19.assignment3.Assignment3Suite;
import gradingTools.comp533s19.assignment4.Assignment4Suite;
import trace.grader.basics.GraderBasicsTraceUtility;
import util.trace.Tracer;

public class RunA4Tests {

	public static void main(String[] args) {
		Tracer.showInfo(true);
		GraderBasicsTraceUtility.setBufferTracedMessages(false);
		Assignment4Suite.setProcessTimeOut(45);
	Assignment4Suite.main(args);
	}

}
