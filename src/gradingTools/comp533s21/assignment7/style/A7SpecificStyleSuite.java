package gradingTools.comp533s21.assignment7.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A7TaggedClassesDefined.class,
//	A7ExpectedInterfaces.class,
	A7ExpectedCalls.class,
//	A7SpuriousCalls.class,
//	A6ExpectedGetters.class,
//	A6ExpectedSetters.class,
	A7ExpectedSignatures.class,
	
	
})
	

//@MaxValue(50)
public class A7SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A7SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

