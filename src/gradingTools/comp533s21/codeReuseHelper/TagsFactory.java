package gradingTools.comp533s21.codeReuseHelper;

public class TagsFactory {

	private static AssignmentTags selectTag = null;
	
	public static void setAssignmentTags(AssignmentTags at) {
		selectTag=at;
	}
	
	public static AssignmentTags getAssignmentTags() {
		return selectTag;
	}
	
}
