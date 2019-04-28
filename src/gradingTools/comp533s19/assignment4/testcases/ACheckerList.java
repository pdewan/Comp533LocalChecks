package gradingTools.comp533s19.assignment4.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gradingTools.comp533s19.assignment4.testcases.CheckerList;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import gradingTools.shared.testcases.utils.LinesMatchKind;
import gradingTools.shared.testcases.utils.LinesMatcher;

public class ACheckerList implements CheckerList {
	protected List<SubstringSequenceChecker> checkers = new ArrayList();
	protected List<String[]> unmatchedStrings = new ArrayList();
	public ACheckerList(SubstringSequenceChecker[] aCheckers) {
		checkers = Arrays.asList(aCheckers);
	}
	public ACheckerList() {
		
	}
	@Override
	public void add(SubstringSequenceChecker aChecker) {
		checkers.add(aChecker);
	}
	@Override
	public  boolean check(StringBuffer aStringBuffer) {
		boolean retVal = true;
		for (SubstringSequenceChecker aChecker:checkers) {
			retVal = aChecker.check(aStringBuffer) && retVal;
		}
		return retVal;
	}
	@Override
	public  boolean check(String aString) {
		boolean retVal = true;
		for (SubstringSequenceChecker aChecker:checkers) {
			retVal = aChecker.check(aString) && retVal;
		}
		return retVal;
	}
	
	@Override
	public List<String[]> getUnmatchedStrings() {
		unmatchedStrings.clear();
		for (SubstringSequenceChecker aChecker:checkers) {
			if (!aChecker.isSuccess()) {
				unmatchedStrings.add(aChecker.getSubstrings());
			}
		}
		return unmatchedStrings;
		
	}
//	@Override
//	public double getMyWeight() {
//		double retVal = 0.;
//		for (SubstringSequenceChecker aChecker:checkers) {
//			if (aChecker.isSuccess()) {
//				retVal += aChecker.getMyWeight();
//			}
//		}
//		return retVal;
//	}
	@Override
	public boolean check(LinesMatcher aLinesMatcher, LinesMatchKind aMatchKind,
			int aFlags) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
