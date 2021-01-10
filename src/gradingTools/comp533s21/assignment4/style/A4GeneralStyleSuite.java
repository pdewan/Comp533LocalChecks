package gradingTools.comp533s21.assignment4.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A4NoCheckstyleWarnings.class,
	A4NamedConstants.class,
	A4PublicMethodsOverride.class,
	A4InterfaceAsType.class,
	A4MnemonicNames.class,
	A4AccessModifiersMatched.class,
//	InterpolatedReuse.class,
//	UtilityJavaDocs.class
	
	
})
	

//@MaxValue(50)
public class A4GeneralStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A4GeneralStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

