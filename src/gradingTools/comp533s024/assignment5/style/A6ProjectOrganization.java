package gradingTools.comp533s024.assignment5.style;

import gradingTools.shared.testcases.packageStructure.AbstractFolderMatchesTemplateTest;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
import gradingTools.fileTree.Template;
@MaxValue(10)
@IsExtra(true)
public class A6ProjectOrganization extends AbstractFolderMatchesTemplateTest {

	private final String [] rawTemplate= {
		"|- .*",
		" |- [a-z]?.*[Cc]lient.*",
		"  |~ .*Client.*.java",
		"  |~ .*Client.*.java",
		"  |~ Client.*Simulation.*.java",
		"  |~ Client.*Listener.*.java",
		"  |- [a-z]?.*[Rr]emote.*",
		"   |~ .*[Pp]roxy.*.java",
		"   |~ .*[Pp]roxy.*.java",
		"   |~ .*[Rr][Mm][Ii].*.java",
		"   |~ .*[Gg][Ii][Pp][Cc].*.java",	
		"   |~ .*[Nn][Ii][Oo].*.java",
		" |- [a-z]?.*[Rr]egistry.*",
		"  |~ .*Registry.*.java",
		" |- [a-z]?.*[Ss]erver.*",
		"  |~ .*Server.*.java",
		"  |~ .*Server.*.java",
		"  |~ .*Server.*Simulation.*.java",
		"  |- [a-z]?.*[Rr]emote.*",
		"   |~ .*[Pp]roxy.*.java",
		"   |~ .*[Pp]roxy.*.java",
		"   |~ .*[Rr][Mm][Ii].*.java",
		"   |~ .*[Gg][Ii][Pp][Cc].*.java",	
		"   |~ .*[Nn][Ii][Oo].*.java",
	};
	
	private final Template template = new Template(rawTemplate, "Client Server Package Organization");
	
	@Override
	protected Template getTemplate() {
		return template;
	}

	@Override
	protected double getAcceptabilityCutoff() {
		return 0.95;
	}

}

