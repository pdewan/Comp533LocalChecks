package gradingTools.comp533s21.assignment2.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A2NoCheckstyleWarnings.class,
	A2NamedConstants.class,
	A2PublicMethodsOverride.class,
	A2InterfaceAsType.class,
	A2MnemonicNames.class,
	A2AccessModifiersMatched.class,
//	InterpolatedReuse.class,
//	UtilityJavaDocs.class
	
	
})
	

//@MaxValue(50)
public class A2GeneralStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A2GeneralStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

