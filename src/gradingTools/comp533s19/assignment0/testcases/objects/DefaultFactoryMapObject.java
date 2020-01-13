package gradingTools.comp533s19.assignment0.testcases.objects;

import static org.junit.Assert.assertTrue;

import grader.basics.junit.BasicJUnitUtils;
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
import util.annotations.Explanation;
import util.annotations.MaxValue;
@Explanation("Tests the map object returned by the configured map factory")
@MaxValue(5)
public class DefaultFactoryMapObject extends PassFailJUnitTestCase{
	
    public static final String NAME_1 = "Abbott";
    public static final String NAME_2 = "Longbottom";
    public static final String NAME_3 = "Zabini";
    public static final double NAME_1_CREDIT = 0.3;
    public static final double NAME_2_CREDIT = 0.6;
    public static final double NAME_3_CREDIT = 1.0;

//			".*View:java.beans.PropertyChangeEvent.propertyName=Result; oldValue=null; newValue=.Abbott=1, Zabini=1, Creevey=1, Weasley=1, Dumbledore=2, Potter=2, Longbottom=1, Snape=1, Voldemort=2.; propagationId=null; source=Model..*",
    protected String toKeyValueRegex(String aName){
    	return " *\\(" + aName +  " *, *" + 1 + " *" + "\\) *";
    }
    
    protected String test(TestMapper aMapper, String aName) {
    	Object retVal = aMapper.map(aName);
		if (retVal == null) {
			return "Map function with input returned null";

		}
		String anObjectString = retVal.toString();
//		String aRegex = ".*" + aName + ".*" + 1 + ".*";
		String aRegex = toKeyValueRegex(aName);

		boolean aMatch = anObjectString.matches(aRegex);
		if (!aMatch) {
			return "Map function with input " + aName + " returned " + anObjectString + " which does not match regex:" + aRegex;
		}
		return null;
    }
    
    protected String message(String aName, String anActualVal, String aDesiredVal) {
    	return "Map function with input " + aName + " returned " + anActualVal + " instead of " + aDesiredVal;
    }
    protected TestMapper mapper;

	public TestMapper getMapper() {
		return mapper;
	}
	protected void setMapper() {
		MapperFactory aMapperFactory = (MapperFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(MapperFactory.class);
		mapper = aMapperFactory.getMapper();
		if (mapper == null) {
    		BasicJUnitUtils.assertTrue("Mapper factory returned a null mapper", 0, false);

		}
	}
	protected void testMapper() {
		String aFirstTest = test(mapper, NAME_1);
		if (aFirstTest != null) {
			BasicJUnitUtils.assertTrue(aFirstTest, 0, false);
		}
		
		
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
//		MapperFactory aMapperFactory = (MapperFactory) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(MapperFactory.class);
//		mapper = aMapperFactory.getMapper();
		setMapper();
//		if (mapper == null) {
//			fail ("Mapper factory returned a null mapper");
//		}
//		String aFirstTest = test(mapper, NAME_1);
//		if (aFirstTest != null) {
//			return fail(aFirstTest);
//		}
		testMapper();
		
		return pass();
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getMapperFactory();
		
	}

}
