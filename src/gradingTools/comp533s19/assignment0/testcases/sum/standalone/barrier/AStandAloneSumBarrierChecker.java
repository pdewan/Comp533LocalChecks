package gradingTools.comp533s19.assignment0.testcases.sum.standalone.barrier;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSumBarrierChecker extends ASubstringSequenceChecker{
	
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

	public AStandAloneSumBarrierChecker() {
		init( mySubstrings());
	}
	

}
