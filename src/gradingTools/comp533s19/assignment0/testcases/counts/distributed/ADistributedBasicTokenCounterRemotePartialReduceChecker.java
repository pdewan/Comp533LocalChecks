package gradingTools.comp533s19.assignment0.testcases.counts.distributed;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class ADistributedBasicTokenCounterRemotePartialReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions

	String clientName;
//	Received output from MapReduce Server: Thread[main,5,main]:Controller:Quitting
//	(MapReduce Server)java.lang.InterruptedException
//	(MapReduce Server)java.lang.InterruptedException
//	(MapReduce Server)java.lang.InterruptedException
//	Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@7c2ac017:Quitting
//	MapReduce Client 1: Thread[main,5,main]:exit
//	I***(BasicRunningProject) Received output from MapReduce Client 2: Thread[main,5,main]:exit

	
	public ADistributedBasicTokenCounterRemotePartialReduceChecker() {
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
				    
				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUITTING),
					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING), 
					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING), 
					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  ".*" + AMapReduceTracer.QUITTING), 
					toRegex("MapReduce Client" + ".*" +  AMapReduceTracer.EXIT), 
					toRegex("MapReduce Client" + ".*" +  AMapReduceTracer.EXIT), 


				    toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "Remote List"),
					toRegex(clientName + ".*" + "RMI TCP Connection" +   ".*:.*" + "reduce:"),
//				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote Result"),

					
//					*1.*2",
//					".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
//					".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//				)

//			"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//				toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
		};
		return MY_SUBSTRINGS;
	}
//	public ADistributedBasicTokenCounterRemotePartialReduceChecker() {
//		init( MY_SUBSTRINGS);
//	}
	

}
