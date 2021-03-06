package gradingTools.comp533s19.assignment4.testcases.simulation.blocking_rpc;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.Assignment4Suite;
import util.annotations.Comp533Tags;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(2)
public class BlockingRPCSimulationClientTagged extends SingleClassTagListTestCase {

	public BlockingRPCSimulationClientTagged() {
		super(Assignment4Suite.SIMULATION_CLIENT_TAGS);
	}

}
