package gradingTools.comp533s21.assignment6.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A6TaggedClassesDefined.class,
	A6ExpectedInterfaces.class,
	A6ExpectedCalls.class,
	A6SpuriousCalls.class,
//	A6ExpectedGetters.class,
//	A6ExpectedSetters.class,
	A6ExpectedSignatures.class,
	
	
})
	

//@MaxValue(50)
public class A6SpecificStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A6SpecificStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

