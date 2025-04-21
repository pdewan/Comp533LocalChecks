package gradingTools.comp533s21.assignment7.style;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.junit.BasicJUnitUtils;
import util.annotations.IsExtra;


@RunWith(Suite.class)
@IsExtra(true)
@Suite.SuiteClasses({
	A7NoCheckstyleWarnings.class,
	A7NamedConstants.class,
	A7PublicMethodsOverride.class,
	A7InterfaceAsType.class,
	A7MnemonicNames.class,
	A7AccessModifiersMatched.class,
//	InterpolatedReuse.class,
//	UtilityJavaDocs.class
	
	
})
	

//@MaxValue(50)
public class A7GeneralStyleSuite {

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);

			BasicJUnitUtils.interactiveTest(A7GeneralStyleSuite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A1.xml");
//	}
}

	

