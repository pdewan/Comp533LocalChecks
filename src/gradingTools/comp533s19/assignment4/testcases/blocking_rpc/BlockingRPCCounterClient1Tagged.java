package gradingTools.comp533s19.assignment4.testcases.blocking_rpc;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import util.annotations.Comp533Tags;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(2)
public class BlockingRPCCounterClient1Tagged extends SingleClassTagListTestCase {

	public BlockingRPCCounterClient1Tagged() {
		super(Comp533Tags.BLOCKING_RPC_CLIENT1);
	}

}
