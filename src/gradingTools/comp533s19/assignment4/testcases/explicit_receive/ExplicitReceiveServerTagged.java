package gradingTools.comp533s19.assignment4.testcases.explicit_receive;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import util.annotations.Comp533Tags;
import util.annotations.MaxValue;
import util.tags.DistributedTags;
@MaxValue(2)
public class ExplicitReceiveServerTagged extends SingleClassTagListTestCase {

	public ExplicitReceiveServerTagged() {
		super(Comp533Tags.EXPLICIT_RECEIVE_SERVER);
	}

}
