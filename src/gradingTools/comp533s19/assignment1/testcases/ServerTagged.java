package gradingTools.comp533s19.assignment1.testcases;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(5)
public class ServerTagged extends SingleClassTagListTestCase {

	public ServerTagged() {
//		super(DistributedTags.SERVER);
		super(Assignment1Suite.serverTags);

	}

}
