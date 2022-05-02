package gradingTools.comp533s23.projectOganization;

import gradingTools.fileTree.Template;
import gradingTools.shared.testcases.packageStructure.AbstractFolderMatchesTemplateTest;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(10)
@IsExtra(true)
public class A2ProjectOrganization extends AbstractFolderMatchesTemplateTest {

	private final String [] rawTemplate= {
		"|- .*",
		" |- [a-z]?.*[Ss]ingletons.*",
		"  |~ .*Map.*.java",
		"  |~ .*Map.*Factory.*.java",
		"  |~ .*Counting.*Map.*.java",
		"  |~ .*Summing.*Map.*.java",
		"  |~ .*Reduce.*.java",
		"  |~ .*Reduce.*.java",
		"  |~ .*Reduce.*Factory.*.java",
		"  |~ .*Partition.*.java",
		"  |~ .*Partition.*.java",
		"  |~ .*Partition.*Factory.*.java",
		" |- [a-z]?.*mvc.*",
		"  |~ .*Controller.*.java",
		"  |~ .*Model.*.java",
		"  |~ .*View.*.java",
		"  |~ .*Controller.*.java",
		"  |~ .*Model.*.java",
		"  |~ .*View.*.java",
		" |- [a-z]?.*",
		"  |~ .*KeyValue.*.java",
		"  |~ .*KeyValue.*.java",
		"  |~ .*Main.*.java",
		"  |~ .*Main.*.java",
		" |- [a-z]?.*[Cc]oncurrent.*",
		"  |~ .*Barrier.*.java",
		"  |~ .*Barrier.*.java",
		"  |~ .*Joiner.*.java",
		"  |~ .*Joiner.*.java",
		"  |~ .*Slave.*.java",
		"  |~ .*Slave.*.java",

	};
	
	private final Template template = new Template(rawTemplate, "Assignment 1 Organization");
	
	@Override
	protected Template getTemplate() {
		return template;
	}

	@Override
	protected double getAcceptabilityCutoff() {
		return 0.95;
	}

	
}

