package gradingTools.comp533s22.assignment5.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp533s21.assignment5.style.A5ExpectedCalls;
import gradingTools.comp533s21.assignment5.style.A5ExpectedInterfaces;
import gradingTools.comp533s21.assignment5.style.A5ExpectedSignatures;
import gradingTools.comp533s21.assignment5.style.A5SpuriousCalls;
import gradingTools.comp533s21.assignment5.style.A5TaggedClassesDefined;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A5TaggedClassesDefined.class,
	A5ExpectedInterfaces.class,
	A5ExpectedCalls.class,
	A5SpuriousCalls.class,
	A5ExpectedSignatures.class,
	A5ProjectOrganization.class
	
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
}

	

