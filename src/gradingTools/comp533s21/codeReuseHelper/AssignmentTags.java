package gradingTools.comp533s21.codeReuseHelper;

import java.util.List;

public interface AssignmentTags {

	public List<String> getOneClientClientTags(boolean doNIO, boolean doRMI, boolean doGIPC);
	public List<String> getOneClientServerTags(boolean doNIO, boolean doRMI, boolean doGIPC);
	public List<String> getTwoClientClientTags();
	public List<String> getTwoClientServerTags();
}
