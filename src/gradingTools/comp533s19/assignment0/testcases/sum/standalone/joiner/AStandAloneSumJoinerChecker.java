package gradingTools.comp533s19.assignment0.testcases.sum.standalone.joiner;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneSumJoinerChecker extends ASubstringSequenceChecker{
	public  String[] mySubstrings() {
		return new String[] {
			toLineRegex("main", AMapReduceTracer.ENQUEUE, null, null),
			toLineRegex("main", AMapReduceTracer.ENQUEUE, null, null),
			toLineRegex("main", AMapReduceTracer.ENQUEUE, null, null),
			toLineRegex("main", AMapReduceTracer.JOINER_WAIT_START),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.JOINER_FINISHED_TASK),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.JOINER_FINISHED_TASK),
			toLineRegex(AMapReduceTracer.SLAVE, AMapReduceTracer.JOINER_FINISHED_TASK),
			toLineRegex("main", AMapReduceTracer.JOINER_WAIT_END),

		};
	}

	public AStandAloneSumJoinerChecker() {
		init( mySubstrings());
	}
}
