package gradingTools.comp533s21.assignment1.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A1NoCheckstyleWarnings.class,
	A1NamedConstants.class,
	A1PublicMethodsOverride.class,
	A1InterfaceAsType.class,
	A1MnemonicNames.class,
	A1AccessModifiersMatched.class,
//	InterpolatedReuse.class,
//	UtilityJavaDocs.class
	
	
})
	

//@MaxValue(50)
public class A1GeneralStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A1GeneralStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

