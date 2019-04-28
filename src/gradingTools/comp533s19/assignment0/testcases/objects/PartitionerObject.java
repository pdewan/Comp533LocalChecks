package gradingTools.comp533s19.assignment0.testcases.objects;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.interfaces.TestPartitioner;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;

public class PartitionerObject extends PassFailJUnitTestCase{

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		PartitionerFactory aPartitionerFactory = (PartitionerFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(PartitionerFactory.class);
		TestPartitioner aPartitioner = aPartitionerFactory.getPartitioner();
		int aPartition = aPartitioner.getPartition("aaa", null, 3);
		if (aPartition == 0) {
			return pass();
		}
		
		return fail("bad partition for aaa:" + aPartition);
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getPartitionerFactory();
		
	}

}
