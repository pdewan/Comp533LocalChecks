package gradingTools.comp533s21.assignment3.facebookMapReduce.tests;


import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.execution.BasicRunningProject;
import grader.basics.junit.JUnitTestsEnvironment;
import gradingTools.comp533s19.assignment0.Assignment0Suite;
import gradingTools.comp533s19.assignment0.testcases.MapReduceInputGenerator;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration;
import gradingTools.comp533s21.assignment3.A3ConfigurationProvided;
import gradingTools.comp533s21.assignment3.facebookMapReduce.MultiThreadFacebookMapReduceResult;
import gradingTools.utils.RunningProjectUtils;

public class DistributedFacebookMapReduceResult extends MultiThreadFacebookMapReduceResult {
	public DistributedFacebookMapReduceResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}

	MapReduceConfiguration testMapReduceConfiguration;

	protected String processName() {
		return DistributedTokenCountResult.MAP_REDUCE_SERVER;
	}
	@Override
	protected void callOrForkMain(boolean aFork) throws Throwable {
		BasicRunningProject.setProcessTeamOutputSleepTime(10000);

//		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		
		testMapReduceConfiguration = aConfigurationProvided.getTestConfiguration();
		DistributedTokenCountResult.setupProcesses(	testMapReduceConfiguration.getServerFacebookMapReduce().getName(),
				testMapReduceConfiguration.getRemoteClientFacebookMapReduce().getName());



		interactiveInputProject = RunningProjectUtils.runProject(project, 
				Assignment0Suite.getProcessTimeOut(), new MapReduceInputGenerator (getInputLines()));
		interactiveInputProject.await();
		BasicStaticConfigurationUtils.setBasicCommandToDefaultEntryTagCommand();
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().resetProcessTeams();


	}
	protected void setMainClass() {
		A3ConfigurationProvided aConfigurationProvided = (A3ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A3ConfigurationProvided.class);

		MapReduceConfiguration aTestMapReduceConfiguration =  aConfigurationProvided.getTestConfiguration();
        if (aTestMapReduceConfiguration == null) {
        	assertTrue("No configuration", false);
        }
        Class aFacebookMapReducer = aTestMapReduceConfiguration.getStandAloneFacebookMapReduce();
        if (aFacebookMapReducer == null) {
        	assertTrue("No facebook map reduce", false);
        }
        setMainClass(aFacebookMapReducer);

	}

	public void defaultTest() {
    	passfailDefaultTest();
    }

}
