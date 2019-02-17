package gradingTools.comp533s19.assignment1.testcases;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(5)
public class ClientTagged extends SingleClassTagListTestCase {

	public ClientTagged() {
		super(Assignment1Suite.clientTags);
	}

}
