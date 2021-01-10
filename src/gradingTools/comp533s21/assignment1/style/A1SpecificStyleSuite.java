package gradingTools.comp533s21.assignment1.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A1ClassIsGeneric.class,
	A1ExpectedCalls.class,
	A1ExpectedGetters.class,
//	A1ExpectedInterfaces.class,
	A1ExpectedSetters.class,
	A1ExpectedSignatures.class,
	A1ExpectedSupertypes.class
	
	
})
	

//@MaxValue(50)
public class A1SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A1SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

