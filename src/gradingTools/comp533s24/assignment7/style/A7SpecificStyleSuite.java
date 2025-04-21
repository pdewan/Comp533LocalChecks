package gradingTools.comp533s24.assignment7.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s21.assignment7.style.A7ExpectedCalls;
import gradingTools.comp533s21.assignment7.style.A7ExpectedSignatures;
import gradingTools.comp533s21.assignment7.style.A7TaggedClassesDefined;
import util.annotations.IsExtra;


@RunWith(Suite.class)
@IsExtra(true)
@Suite.SuiteClasses({
	A7TaggedClassesDefined.class,
//	A7ExpectedInterfaces.class,
	A7ExpectedCalls.class,
//	A7SpuriousCalls.class,
//	A6ExpectedGetters.class,
//	A6ExpectedSetters.class,
	A7ExpectedSignatures.class,
//	A7ProjectOrganization.class,
	
	
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

	

