package gradingTools.comp533s22.assignment1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadSumResult;
import gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread.StandAloneSingleThreadSumFactoryChange;
import gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread.StandAloneSingleThreadSumMVC;
import gradingTools.comp533s22.assignment1.listUpdatedTests.IntSummingListMapper;
import gradingTools.comp533s22.assignment1.listUpdatedTests.StandAloneSingleThreadSumMapReduceListImplementation;
import util.annotations.MaxValue;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	IntSummingListMapper.class,
	StandAloneSingleThreadSumResult.class,
	StandAloneSingleThreadSumMVC.class,
	StandAloneSingleThreadSumMapReduceListImplementation.class,
	StandAloneSingleThreadSumFactoryChange.class

})
	

@MaxValue(70)	
public class SingleThreadIntSum extends Assignment1Suite{

}
