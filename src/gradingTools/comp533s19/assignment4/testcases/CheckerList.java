package gradingTools.comp533s19.assignment4.testcases;

import java.util.List;

import gradingTools.comp533s19.assignment4.testcases.SubstringChecker;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;

public interface CheckerList extends SubstringChecker {

	public abstract void add(SubstringSequenceChecker aChecker);

	public abstract List<String[]> getUnmatchedStrings();

}