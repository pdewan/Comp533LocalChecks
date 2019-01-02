package gradingTools.comp533s19.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ClientTagged;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ServerTagged;
import gradingTools.comp533s19.assignment1.testcases.StaticArguments;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClientTagged.class,
	ServerTagged.class,
	StaticArguments.class,
	OneClientConnection.class,
	OneClientReadWriteNonAtomic.class,
	OneClientReadWriteAtomic.class,
	OneClientThreadsAtomic.class,
	OneClientThreadsNonAtomic.class,
	OneClientMessageRatioAtomic.class,
	OneClientMessageRatioNonAtomic.class,
	

	
})
public class Assignment1Suite {
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(Assignment1Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
