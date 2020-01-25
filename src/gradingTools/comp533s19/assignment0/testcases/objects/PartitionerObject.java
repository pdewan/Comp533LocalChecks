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
import util.annotations.MaxValue;
@MaxValue(10)
public class PartitionerObject extends PassFailJUnitTestCase{
	
    public static final String PARTITION_1_NAME = "Abbott";
    public static final String PARTITION_2_NAME = "Longbottom";
    public static final String PARTITION_3_NAME = "Zabini";
    public static final double PARTITION_1_CREDIT = 0.3;
    public static final double PARTITION_2_CREDIT = 0.6;
    public static final double PARTITION_3_CREDIT = 1.0;

//			".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Abbott=1, Zabini=1, Creevey=1, Weasley=1, Dumbledore=2, Potter=2, Longbottom=1, Snape=1, Voldemort=2.; propagationId=null; source=Model..*",


	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		PartitionerFactory aPartitionerFactory = (PartitionerFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(PartitionerFactory.class);
		TestPartitioner aPartitioner = aPartitionerFactory.getPartitioner();
		int aPartition = aPartitioner.getPartition(PARTITION_1_NAME , null, 3);
//		if (aPartition == 0) {
//			return pass();
//		}
		if (aPartition != 0) {
			return fail("Partition for " + PARTITION_1_NAME + " should be:" + 0);
		}
		aPartition = aPartitioner.getPartition(PARTITION_2_NAME , null, 3);
		if (aPartition != 1) {
			return partialPass(PARTITION_1_CREDIT, "Partition for " + PARTITION_2_NAME + " should be:" + 1);
		}
		aPartition = aPartitioner.getPartition(PARTITION_3_NAME , null, 3);
		if (aPartition != 2) {
			return partialPass(PARTITION_2_CREDIT, "Partition for " + PARTITION_3_NAME + " should be:" + 3);
		}
		return pass();
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getPartitionerFactory();
		
	}

}
