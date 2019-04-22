package gradingTools.comp533s19.assignment4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient1CounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient1CounterReceives;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient1Tagged;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient2CounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient2CounterReceives;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient2Tagged;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterTwoQueues;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterReceives;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerTagged;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ExplicitReceiveServerTagged.class,
	ExplicitReceiveClient1Tagged.class,
	ExplicitReceiveClient2Tagged.class,
	ExplicitReceiveServerCounterRegularOutput.class,
	ExplicitReceiveClient1CounterRegularOutput.class,
	ExplicitReceiveClient2CounterRegularOutput.class,
	ExplicitReceiveServerCounterReceives.class,
	ExplicitReceiveClient1CounterReceives.class,
	ExplicitReceiveClient2CounterReceives.class,
	ExplicitReceiveServerCounterTwoQueues.class
//	Assignment1OneClientSuite.class,
//	Assignment1TwoClientSuite.class,


})
public class CounterExplicitReceiveSuite {
	
}
