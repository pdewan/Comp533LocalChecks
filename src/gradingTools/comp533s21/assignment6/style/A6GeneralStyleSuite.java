package gradingTools.comp533s21.assignment6.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	A6NoCheckstyleWarnings.class,
	A6NamedConstants.class,
	A6PublicMethodsOverride.class,
	A6InterfaceAsType.class,
	A6MnemonicNames.class,
	A6AccessModifiersMatched.class,
//	InterpolatedReuse.class,
//	UtilityJavaDocs.class
	
	
})
	

//@MaxValue(50)
public class A6GeneralStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A6GeneralStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

