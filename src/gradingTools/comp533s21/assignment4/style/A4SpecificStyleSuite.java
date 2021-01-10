package gradingTools.comp533s21.assignment4.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A4TaggedClassesDefined.class,
	A4ExpectedInterfaces.class,
	A4ExpectedCalls.class,
	A4SpuriousCalls.class,
	A4ExpectedGetters.class,
	A4ExpectedSetters.class,
	A4ExpectedSignatures.class,
	
	
})
	

//@MaxValue(50)
public class A4SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A4SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

