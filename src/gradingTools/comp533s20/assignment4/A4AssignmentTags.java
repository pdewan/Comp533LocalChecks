package gradingTools.comp533s20.assignment4;

import java.util.Arrays;
import java.util.List;

import gradingTools.comp533s21.codeReuseHelper.AssignmentTags;
import util.tags.DistributedTags;

public class A4AssignmentTags implements AssignmentTags{

	protected List<String> twoClientClient=null;
	protected List<String> twoClientServer=null;
	
	@Override
	public List<String> getTwoClientClientTags() {
		if(twoClientClient!=null)
			return twoClientClient;
		generateTwoClientTags();
		return twoClientClient;
	}

	@Override
	public List<String> getTwoClientServerTags() {
		if(twoClientServer!=null)
			return twoClientServer;
		generateTwoClientTags();
		return twoClientServer;
	}

	@Override
	public List<String> getOneClientClientTags(boolean doNIO, boolean doRMI, boolean doGIPC) {
		List<String> aClientTags = null;
		if (doRMI) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI);
		}
		if (doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.GIPC);
		}
		if (doNIO) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO, DistributedTags.RMI, DistributedTags.GIPC);
			if (!doGIPC && !doRMI) {
				aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO);
			}
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
		if (doNIO) {
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO, DistributedTags.RMI, DistributedTags.GIPC);
			if (!doGIPC && !doRMI) {
				aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO);
			}
		}
		return aServerTags;
	}

	protected void generateTwoClientTags() {
		twoClientClient = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI);
		twoClientServer = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI);
	}
	

	
}
