package gradingTools.comp533s22.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadTokenCountResult;
import gradingTools.comp533s20.assignment1.testcases.counts.standalone.singlethread.StandAloneSingleThreadTokenCountMVC;
import gradingTools.comp533s22.assignment1.listUpdatedTests.StandAloneSingleThreadTokenCountMapReduceListImplementation;
import gradingTools.comp533s22.assignment1.listUpdatedTests.TokenCountingListMapper;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	TokenCountingListMapper.class,
	StandAloneSingleThreadTokenCountResult.class,
	StandAloneSingleThreadTokenCountMVC.class,
	StandAloneSingleThreadTokenCountMapReduceListImplementation.class,

})
	

public class SingleThreadTokenCount extends Assignment1Suite{

}
