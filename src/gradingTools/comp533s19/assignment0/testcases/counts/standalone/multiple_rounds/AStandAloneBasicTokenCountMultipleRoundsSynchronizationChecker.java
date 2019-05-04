package gradingTools.comp533s19.assignment0.testcases.counts.standalone.multiple_rounds;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneBasicTokenCountMultipleRoundsSynchronizationChecker extends ASubstringSequenceChecker{
	public  String[] mySubstrings() {
		return new String[] {
		
			toLineRegex("main", AMapReduceTracer.NOTIFY),
			toLineRegex("main", AMapReduceTracer.NOTIFY),
			toLineRegex("main", AMapReduceTracer.NOTIFY),
//			toLineRegex("main", AMapReduceTracer.JOINER_WAIT_END),
			toLineRegex(AMapReduceTracer.SLAVE, ":" + AMapReduceTracer.WAIT),
			toLineRegex(AMapReduceTracer.SLAVE, ":" + AMapReduceTracer.WAIT),
			toLineRegex(AMapReduceTracer.SLAVE, ":" + AMapReduceTracer.WAIT),
		};
	}

	public AStandAloneBasicTokenCountMultipleRoundsSynchronizationChecker() {
		init( mySubstrings());
	}
}
