package gradingTools.comp533s19.assignment4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient1CounterBlockingProcedure;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient1CounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient1Tagged;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient2CounterBlockingProcedure;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient2CounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCClient2Tagged;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCServerCounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.BlockingRPCServerTagged;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	BlockingRPCServerTagged.class,
	BlockingRPCClient1Tagged.class,
	BlockingRPCClient2Tagged.class,
	BlockingRPCServerCounterRegularOutput.class,
	BlockingRPCClient1CounterRegularOutput.class,
	BlockingRPCClient2CounterRegularOutput.class,
	BlockingRPCClient1CounterBlockingProcedure.class,
	BlockingRPCClient2CounterBlockingProcedure.class,
	


//	ExplicitReceiveClient1CounterRegularOutput.class,
//	ExplicitReceiveClient2CounterRegularOutput.class,
//	ExplicitReceiveServerCounterReceives.class,
//	ExplicitReceiveClient1CounterReceives.class,
//	ExplicitReceiveClient2CounterReceives.class,
//	ExplicitReceiveServerCounterTwoQueues.class
//	Assignment1OneClientSuite.class,
//	Assignment1TwoClientSuite.class,


})
public class CounterBlockingRPCSuite {
	
}
