package gradingTools.comp533s21.assignment3.facebookMapReduce;

import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s21.assignment3.facebookMapReduce.checkers.AStandAloneFacebookMapReduceResultChecker;
import gradingTools.shared.testcases.SubstringSequenceChecker;

public class MultiThreadFacebookMapReduceResult extends MultiThreadTokenCountResult {
	public MultiThreadFacebookMapReduceResult() {
//		BasicProjectExecution.setProcessTimeOut(Assignment0Suite.getProcessTimeOut());
	}

	protected SubstringSequenceChecker checker() {
		return new AStandAloneFacebookMapReduceResultChecker();
	}

	@Override 
	public String[] getInputLines() {
		return new String[] {"3", 
				"a,b,c,d b,a,c,d,e c,a,b,d,e d,a,b,c,e e,b,c,d",
//				"a,x,y x,a,y y,a,x",
				"Harry,Herminone,Ron,Dumbledore Herminone,Harry,Ron,Dumbledore,Hagrid Ron,Harry,Herminone,Dumbledore,Hagrid Dumbledore,Harry,Herminone,Ron,Hagrid Hagrid,Herminone,Ron,Dumbledore",
				"quit"
		};
	}
	protected void setMainClass() {
////		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
//		A2ConfigurationProvided aConfigurationProvided = (A2ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(A2ConfigurationProvided.class);
//
//		MapReduceConfiguration aTestMapReduceConfiguration =  aConfigurationProvided.getTestConfiguration();
//        if (aTestMapReduceConfiguration == null) {
//        	assertTrue("No configuration", false);
//        }
//        Class anIntSummer = aTestMapReduceConfiguration.getStandAloneIntegerSummer();
//        if (anIntSummer == null) {
//        	assertTrue("No int summer", false);
//        }
//        setMainClass(anIntSummer);

	}

}
