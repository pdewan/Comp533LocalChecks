package gradingTools.comp533s19.assignment1.testcases;

import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(5)
public class ServerTagged extends SingleClassTaggedTestCase {

	public ServerTagged() {
		super(DistributedTags.SERVER);
	}

}
