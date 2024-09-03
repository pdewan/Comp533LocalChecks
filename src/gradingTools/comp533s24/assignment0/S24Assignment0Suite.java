package gradingTools.comp533s24.assignment0;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.BasicLanguageDependencyManager;
import grader.basics.config.BasicExecutionSpecificationSelector;
import grader.basics.config.BasicStaticConfigurationUtils;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s22.assignment0.A0Style;
import gradingTools.shared.testcases.greeting.GreetingCheckstyle;
import gradingTools.shared.testcases.greeting.GreetingClassRegistryProvided;
import gradingTools.shared.testcases.greeting.GreetingMainProvided;
import gradingTools.shared.testcases.greeting.GreetingRun;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	GreetingClassRegistryProvided.class,
	GreetingMainProvided.class,
	GreetingRun.class,
	GreetingCheckstyle.class,
	A0Style.class
//	SocialDistanceMainProvided.class,
//	SocialDistanceMainRuns.class,
//	LispGreetingLoad.class
// 2.1, 3.1, 4.1, 5.2, 6.1
// 6
})
	

//@MaxValue(50)
public class S24Assignment0Suite {
	public static void configureProperties() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
		setCheckStyleConfiguration("unc_checks_533_A0.xml");
	}
	static {
		configureProperties();
	}

	public static void main (String[] args) {
		try {
//			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setLanguage(BasicLanguageDependencyManager.JAVA_LANGUAGE);
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
			setCheckStyleConfiguration("unc_checks_533_A0.xml");
			BasicJUnitUtils.interactiveTest(S24Assignment0Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().
//		setCheckStyleConfiguration("unc_checks_524_A0_1.xml");
	}
	
}
