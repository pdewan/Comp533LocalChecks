package gradingTools.comp533s19.assignment0.testcases.counts.distributed;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment4.testcases.ASubstringSequenceChecker;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AClientBasicTokenCountPartialReduceChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingSlave@78308db1:dequeue:(null,null)
//	I***(BasicRunningProject) Received output from main: Thread[Slave0,5,main]:mapreduce.ATokenCountingReducer@660f79bb:reduce:[(Hogwarts,1), (Hogwarts,1), (muggles,1), (wizards,1), (Hogwarts,1), (Hogwarts,1), (Hogwarts,1), (muggles,1), (muggles,1), (wizards,1)]:{Hogwarts=5, muggles=3, wizards=2}
	String clientName;
	String slaveName;
	
	public  final String[] MY_SUBSTRINGS = {
//			I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingClient@27d6c5e0:Remote List:[(Hogwarts,1), (wizards,1), (muggles,1)]
//					I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingReducer@1d45edc8:reduce:[(Hogwarts,1), (wizards,1), (muggles,1)]:{Hogwarts=1, muggles=1, wizards=1}
//					I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingClient@27d6c5e0:Remote Result:{Hogwarts=1, muggles=1, wizards=1}
//					I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave2,5,main]:mapreduce.ATokenCountingSlave@3e3abc88:dequeue:(null,null)
//					I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave2,5,main]:mapreduce.ATokenCountingSlave@3e3abc88:Remote List:[(muggles,1), (Hogwarts,1), (wizards,1)]
//					I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave1,5,main]:mapreduce.ATokenCountingSlave@6ce253f1:Remote Result:{Hogwarts=1, muggles=1, wizards=1}
////			toRegex(
//				AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUIT,
//			    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
//				toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + "Remote List"),
//			    toRegex(DistributedTokenCountResult.MAP_REDUCE_CLIENT_1 + ".*" + "RMI TCP Connection" +   ".*:.*" + "Remote List"),
//				toRegex(DistributedTokenCountResult.MAP_REDUCE_CLIENT_1 + ".*" + "RMI TCP Connection" +   ".*:.*" + "reduce:"),
//			    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + "Remote Result"),
			    
//			    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
//				toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote List"),
			    toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REMOTE_LIST),
				toRegex("RMI TCP Connection" +   ".*:.*" + AMapReduceTracer.REDUCE),
//			    toRegex(clientName + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote Result"),

				
//				*1.*2",
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Hogwarts=5.*"
//				".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.*Potter=2.*",
//			)

//		"(Asynchronous Received Call Invoker|Selecting Thread)..ReceivedMessageDequeued",
//			toPrefixedRegex("I\\*\\*\\*", "Selecting Thread..ReceivedReturnValueQueued")	
	};
//	public ARemoteBasicTokenCounterRemotePartialReduceChecker(String aClientName, String aSlaveName) {
//		clientName = aClientName;
//		slaveName = aSlaveName;
////		init( MY_SUBSTRINGS);
//		init( makeMySubstrings());
//
//	}
	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
//				I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingClient@27d6c5e0:Remote List:[(Hogwarts,1), (wizards,1), (muggles,1)]
//						I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingReducer@1d45edc8:reduce:[(Hogwarts,1), (wizards,1), (muggles,1)]:{Hogwarts=1, muggles=1, wizards=1}
//						I***(BasicRunningProject) Received output from MapReduce Client 1: Thread[RMI TCP Connection(1)-152.2.130.185,5,RMI Runtime]:mapreduce.ATokenCountingClient@27d6c5e0:Remote Result:{Hogwarts=1, muggles=1, wizards=1}
//						I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave2,5,main]:mapreduce.ATokenCountingSlave@3e3abc88:dequeue:(null,null)
//						I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave2,5,main]:mapreduce.ATokenCountingSlave@3e3abc88:Remote List:[(muggles,1), (Hogwarts,1), (wizards,1)]
//						I***(BasicRunningProject) Received output from MapReduce Server: Thread[Slave1,5,main]:mapreduce.ATokenCountingSlave@6ce253f1:Remote Result:{Hogwarts=1, muggles=1, wizards=1}
////				toRegex(
//					AMapReduceTracer.CONTROLLER + ".*" + AMapReduceTracer.QUIT,
//				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
//					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + "Remote List"),
//				    toRegex(DistributedTokenCountResult.MAP_REDUCE_CLIENT_1 + ".*" + "RMI TCP Connection" +   ".*:.*" + "Remote List"),
//					toRegex(DistributedTokenCountResult.MAP_REDUCE_CLIENT_1 + ".*" + "RMI TCP Connection" +   ".*:.*" + "reduce:"),
//				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  "2" + ".*:.*" + "Remote Result"),
				    
//				    toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + AMapReduceTracer.DEQUEUE + ".*null.*null"),
//					toRegex(DistributedTokenCountResult.MAP_REDUCE_SERVER + ".*" + AMapReduceTracer.SLAVE +  slaveName + ".*:.*" + "Remote List"),
				    toRegex("RMI TCP Connection" +   ".*:.*" + "Remote List"),
					toRegex("RMI TCP Connection" +   ".*:.*" + "reduce:"),
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
	public AClientBasicTokenCountPartialReduceChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
