package gradingTools.comp533s19.assignment0.testcases.counts.standalone;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneBasicTokenCounterQuitChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions

	String clientName;
//	Received output from MapReduce Server: Thread[main,5,main]:Controller:Quitting
//	(MapReduce Server)java.lang.InterruptedException
//	(MapReduce Server)java.lang.InterruptedException
//	(MapReduce Server)java.lang.InterruptedException
//	Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@7c2ac017:Quitting
//	MapReduce Client 1: Thread[main,5,main]:exit
//	I***(BasicRunningProject) Received output from MapReduce Client 2: Thread[main,5,main]:exit

	
	public AStandAloneBasicTokenCounterQuitChecker() {
//		clientName = aClientName;
//		init( MY_SUBSTRINGS);
		init( makeMySubstrings());

	}
	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
//				Received output from MapReduce Server: Thread[main,5,main]:Controller:Quitting
//				(MapReduce Server)java.lang.InterruptedException
//				(MapReduce Server)java.lang.InterruptedException
//				(MapReduce Server)java.lang.InterruptedException
//				Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@7c2ac017:Quitting
//				MapReduce Client 1: Thread[main,5,main]:exit
//				I***(BasicRunningProject) Received output from MapReduce Client 2: Thread[main,5,main]:exit
				    
					".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING + ".*", 
					".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING + ".*", 
					".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING + ".*", 
				    toRegex( AMapReduceTracer.CONTROLLER, AMapReduceTracer.QUITTING),


//					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING), 
//					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING), 
//					toRegex(DistributedTokenCountResult.MAP_REDUCE_CLIENT, "RMI TCP Connection", AMapReduceTracer.QUITTING), 
//					".*" + DistributedTokenCountResult.MAP_REDUCE_CLIENT + ".*" + "main.*" + AMapReduceTracer.EXIT + ".*",
//					".*" + DistributedTokenCountResult.MAP_REDUCE_CLIENT + ".*" + "main.*" + AMapReduceTracer.EXIT + ".*",

 

	
		};
		return MY_SUBSTRINGS;
	}
//	public ADistributedBasicTokenCounterRemotePartialReduceChecker() {
//		init( MY_SUBSTRINGS);
//	}
	

}
