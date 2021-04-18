package gradingTools.comp533s20.assignment6;

import java.util.Arrays;
import java.util.List;

import grader.basics.config.BasicExecutionSpecificationSelector;
import gradingTools.comp533s20.assignment4.A4AssignmentTags;
import util.tags.DistributedTags;

public class A6AssignmentTags extends A4AssignmentTags {

	@Override
	public List<String> getOneClientClientTags(boolean doNIO, boolean doRMI, boolean doGIPC) {
		List<String> aClientTags = null;
		if (doRMI && doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
		} else if (doRMI) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO);
		} else if (doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.GIPC);
		} else {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO);
		}
		return aClientTags;
	}

	@Override
	public List<String> getOneClientServerTags(boolean doNIO, boolean doRMI, boolean doGIPC) {
		List<String> aServerTags = null;
		if (doRMI && doGIPC) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
		} else if (doRMI) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO);
		} else if (doGIPC) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.GIPC);
		} else {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO);
		}
		return aServerTags;
	}

	@Override
	protected void generateTwoClientTags() {
		twoClientClient = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
		twoClientServer = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
	}
	

	
}
