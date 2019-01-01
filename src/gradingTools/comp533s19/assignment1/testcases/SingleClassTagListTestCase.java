package gradingTools.comp533s19.assignment1.testcases;

import java.util.Arrays;
import java.util.Set;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;

public class SingleClassTagListTestCase extends PassFailJUnitTestCase {
	private final String[] tags;
	protected final String[] originalTags;
	protected Class lastFoundClass;

	public String[] getTags() {
		return tags;
	}

	public String[] getOriginalTags() {
		return originalTags;
		// return tags;
	}
	public Class getLastFoundClass() {
		return lastFoundClass;
	}

	protected boolean checkAllTags = true;

	public SingleClassTagListTestCase(String... aTags) {
		// super("Prompt printer test case");
//		super("Single class tagged '" + Arrays.toString(aTags) + "' test case");
		this.tags = aTags;
		originalTags = aTags.clone();
	}

	public SingleClassTagListTestCase(boolean aCheckAllTags, String... aTags) {
		this(aTags);
		checkAllTags = aCheckAllTags;

	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean aCurrentCheckAllTags = BasicProjectIntrospection
				.isCheckAllSpecifiedTags();
		if (!checkAllTags) {
			BasicProjectIntrospection.setCheckAllSpecifiedTags(checkAllTags);
		}
		Set<Class> aClasses = BasicProjectIntrospection.findClassesByTag(
				project, tags);
		if (!checkAllTags) {
			BasicProjectIntrospection
					.setCheckAllSpecifiedTags(aCurrentCheckAllTags);
		}
		if (aClasses.size() == 0) {
			return fail("No binary class tagged: " + Arrays.toString(tags));
		}
		lastFoundClass = aClasses.iterator().next();
		if (!checkAllTags) {
			return pass();
		}
		if (aClasses.size() == 1) {
			return pass();
		}
			return partialPass(0.5,
					"Multiple classes tagged:" + Arrays.toString(tags)
							+ " " + aClasses);

//		if (checkAllTags) {
//			if (aClasses.size() == 1) {
//				return pass();
//			}
//			if (aClasses.size() > 1) {
//				return partialPass(0.5,
//						"Multiple classes tagged:" + Arrays.toString(tags)
//								+ " " + aClasses);
//			}
//		} else {
//			if (aClasses.size() > 1) {
//				return pass();
//			}
//		}
//		return fail("No binary class tagged: " + Arrays.toString(tags));
	}
}
