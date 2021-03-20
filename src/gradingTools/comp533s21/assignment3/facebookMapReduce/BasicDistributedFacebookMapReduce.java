package gradingTools.comp533s21.assignment3.facebookMapReduce;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.comp533s21.assignment3.facebookMapReduce.tests.ClientFacebookMapReducePartialReduce;
import gradingTools.comp533s21.assignment3.facebookMapReduce.tests.ClientFacebookMapReduceQuit;
import gradingTools.comp533s21.assignment3.facebookMapReduce.tests.DistributedFacebookMapReducePartialReduce;
import gradingTools.comp533s21.assignment3.facebookMapReduce.tests.DistributedFacebookMapReduceQuit;
import gradingTools.comp533s21.assignment3.facebookMapReduce.tests.DistributedFacebookMapReduceResult;
import util.annotations.IsExtra;
import util.annotations.MaxValue;



@RunWith(Suite.class)
@Suite.SuiteClasses({

	DistributedFacebookMapReduceResult.class,
	DistributedFacebookMapReduceQuit.class,
	DistributedFacebookMapReducePartialReduce.class,
	ClientFacebookMapReducePartialReduce.class,
	ClientFacebookMapReduceQuit.class
})
	

@MaxValue(50)
@IsExtra(true)
public class BasicDistributedFacebookMapReduce extends Assignment1Suite{

}
