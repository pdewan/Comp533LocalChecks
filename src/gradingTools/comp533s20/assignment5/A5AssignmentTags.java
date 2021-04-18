package gradingTools.comp533s20.assignment5;

import java.util.Arrays;
import java.util.List;

import gradingTools.comp533s20.assignment4.A4AssignmentTags;
import util.tags.DistributedTags;

public class A5AssignmentTags extends A4AssignmentTags {

	@Override
	public List<String> getOneClientClientTags(boolean doNIO, boolean doRMI, boolean doGIPC) {
		List<String> aClientTags = null;
		if (doRMI) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI);
		}
		if (doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.GIPC);
		}
		if (!doRMI && !doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO, DistributedTags.RMI, DistributedTags.GIPC);
		}
		return aClientTags;
	}

	@Override
	public List<String> getOneClientServerTags(boolean doNIO, boolean doRMI, boolean doGIPC) {
		List<String> aServerTags = null;
		if (doRMI) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI);
		}
		if (doGIPC) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI,DistributedTags.GIPC);
		}
		if (!doRMI && !doGIPC) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO, DistributedTags.RMI, DistributedTags.GIPC);
		}
		return aServerTags;
	}

	@Override
	protected void generateTwoClientTags() {
		twoClientClient = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.GIPC);
		twoClientServer = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.GIPC);
	}
	

	
}
