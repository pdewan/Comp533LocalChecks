package gradingTools.comp533s21.assignment5.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A5TaggedClassesDefined.class,
	A5ExpectedInterfaces.class,
	A5ExpectedCalls.class,
	A5SpuriousCalls.class,
	A5ExpectedGetters.class,
	A5ExpectedSetters.class,
	A5ExpectedSignatures.class,
	
	
})
	

//@MaxValue(50)
public class A5SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A5SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

