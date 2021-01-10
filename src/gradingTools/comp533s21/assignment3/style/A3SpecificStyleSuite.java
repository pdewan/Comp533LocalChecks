package gradingTools.comp533s21.assignment3.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A3ExpectedInterfaces.class,
	A3ExpectedCalls.class,
	A3SpuriousCalls.class,
	A3ExpectedGetters.class,
	A3ExpectedSetters.class,
	A3ExpectedSignatures.class,
	
	
})
	

//@MaxValue(50)
public class A3SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A3SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

