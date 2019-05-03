package gradingTools.comp533s19.assignment0.testcases.objects;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.interfaces.TestMapper;
import gradingTools.comp533s19.assignment0.interfaces.TestPartitioner;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;

public class TokenCountMapObject extends PassFailJUnitTestCase{
	
    public static final String NAME_1 = "Abbott";
    public static final String NAME_2 = "Longbottom";
    public static final String NAME_3 = "Zabini";
    public static final double NAME_1_CREDIT = 0.3;
    public static final double NAME_2_CREDIT = 0.6;
    public static final double NAME_3_CREDIT = 1.0;

//			".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Abbott=1, Zabini=1, Creevey=1, Weasley=1, Dumbledore=2, Potter=2, Longbottom=1, Snape=1, Voldemort=2.; propagationId=null; source=Model..*",
    protected String toKeyValueRegex(String aName){
    	return ".*" + aName + ".*" + 1 + ".*";
    }
    
    protected String test(TestMapper aMapper, String aName) {
    	Object retVal = aMapper.map(aName);
		if (retVal == null) {
			return "Map function with input returned null";

		}
		String anObjectString = retVal.toString();
		String aRegex = ".*" + aName + ".*" + 1 + ".*";
		boolean aMatch = anObjectString.matches(aRegex);
		if (!aMatch) {
			return "Map function with input " + aName + " returned " + anObjectString + " instead of " + aRegex;
		}
		return null;
    }
    
    protected String message(String aName, String anActualVal, String aDesiredVal) {
    	return "Map function with input " + aName + " returned " + anActualVal + " instead of " + aDesiredVal;
    }
    TestMapper mapper;

	public TestMapper getMapper() {
		return mapper;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		MapperFactory aMapperFactory = (MapperFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(MapperFactory.class);
		mapper = aMapperFactory.getMapper();
		if (mapper == null) {
			fail ("Mapper factory returned a null mapper");
		}
		String aFirstTest = test(mapper, NAME_1);
		if (aFirstTest != null) {
			return fail(aFirstTest);
		}
		
		return pass();
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getMapperFactory();
		
	}

}
