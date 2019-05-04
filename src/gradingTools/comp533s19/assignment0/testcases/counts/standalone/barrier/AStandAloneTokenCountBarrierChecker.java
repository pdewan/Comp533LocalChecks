package gradingTools.comp533s19.assignment0.testcases.counts.standalone.barrier;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneTokenCountBarrierChecker extends ASubstringSequenceChecker{
	
	public  String[] mySubstrings() {
		return new String[] {
//			toLineRegex("main", AMapReduceTracer.BARRIER_CREATED),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.BARRIER_WAIT_START),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.BARRIER_WAIT_START),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.BARRIER_RELEASE_ALL),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.BARRIER_WAIT_END),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.BARRIER_WAIT_END)
		};
	}

	public AStandAloneTokenCountBarrierChecker() {
		init( mySubstrings());
	}
	

}
