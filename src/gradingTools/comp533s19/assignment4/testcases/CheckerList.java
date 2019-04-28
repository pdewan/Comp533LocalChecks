package gradingTools.comp533s19.assignment4.testcases;

import java.util.List;

import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.shared.testcases.interfaces.SubstringChecker;

public interface CheckerList extends SubstringChecker {

	public abstract void add(SubstringSequenceChecker aChecker);

	public abstract List<String[]> getUnmatchedStrings();

}