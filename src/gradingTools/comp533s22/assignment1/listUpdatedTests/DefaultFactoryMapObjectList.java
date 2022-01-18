package gradingTools.comp533s22.assignment1.listUpdatedTests;

import java.util.Arrays;
import java.util.List;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.factories.MapperFactory;
import gradingTools.comp533s21.assignment1.interfaces.TestMapper;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@Explanation("Tests the map object returned by the configured map factory")
@MaxValue(5)
public class DefaultFactoryMapObjectList extends PassFailJUnitTestCase{
	
//    public static final String NAME_1 = "Abbott";
//    public static final String NAME_2 = "Longbottom";
//    public static final String NAME_3 = "Zabini";
//    public static final double NAME_1_CREDIT = 0.3;
//    public static final double NAME_2_CREDIT = 0.6;
//    public static final double NAME_3_CREDIT = 1.0;

	static final String [] INPUTS = {
		"Abbott","Abbott","Abbott","Abbott",
		"Longbottom","Longbottom",
		"Zabini","Zabini","Zabini",
	};
	
	static final String [] input = INPUTS;
	
	private final String aRegex = ".*(\\(Abbott, ?1\\), ?){4}"
			 					+ ".*(\\(Longbottom, ?1\\), ?){2}"
			 					+ ".*(\\(Zabini, ?1\\),? ?){3}.*";	
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
	protected TestCaseResult testMapper() {
		TestMapper aMapper = getMapper();
		List<String> aInputList = Arrays.asList(input);
    	List<Object> retVal = aMapper.map(aInputList);
		if (retVal == null) 
			return fail("Map function with input returned null");
		String anObjectString = retVal.toString();
		
		if(anObjectString.matches(aRegex))
			return pass();
		
		return fail("Map function with input " + aInputList + " returned " + anObjectString + " which does not match regex:" + aRegex);
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
		setMapper();
		return testMapper();
		
	}
	protected Class factoryClass() {
		ConfigurationProvided aConfigurationProvided = (ConfigurationProvided) JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest(ConfigurationProvided.class);
		return aConfigurationProvided.getTestConfiguration().getMapperFactory();
	}

}
