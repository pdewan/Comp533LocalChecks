package gradingTools.comp533s19.assignment4.testcases.custom_rpc;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import util.annotations.Comp533Tags;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(2)
public class CustomRPCCounterServerTagged extends SingleClassTagListTestCase {

	public CustomRPCCounterServerTagged() {
		super(Comp533Tags.CUSTOM_RPC_SERVER);
	}

}
