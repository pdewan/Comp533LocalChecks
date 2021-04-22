package gradingTools.comp533s21.codeReuseHelper;

public class TagsFactory {

	private static AssignmentTags selectTag = null;
	
	public static void setAssignmentTags(AssignmentTags at) {
		selectTag=at;
	}
	
	public static AssignmentTags getAssignmentTags() {
		if(selectTag==null) {
			System.err.println("TagsFactory has never been set in current Assignment Suite");
			System.err.println("Call at: "+new Throwable().getStackTrace()[1]);
		}
		return selectTag;
	}
	
}
