package gradingTools.comp533s21.assignment5.style;

import gradingTools.shared.testcases.utils.TaggedClassesDefined;
import util.annotations.Tags;
import util.tags.DistributedTags;

public class A5TaggedClassesDefined extends TaggedClassesDefined{
	String[][] classesTags = {			
			{DistributedTags.SERVER_REMOTE_INTERFACE, DistributedTags.RMI},
			{DistributedTags.CLIENT_REMOTE_INTERFACE, DistributedTags.RMI},
			{DistributedTags.CLIENT_REMOTE_OBJECT, DistributedTags.RMI},
			{DistributedTags.SERVER_REMOTE_OBJECT, DistributedTags.RMI},
			{DistributedTags.CLIENT_CONFIGURER, DistributedTags.RMI},
			{DistributedTags.SERVER_CONFIGURER, DistributedTags.RMI},
			{DistributedTags.CLIENT, DistributedTags.RMI},
			{DistributedTags.SERVER, DistributedTags.RMI},
			{DistributedTags.CLIENT_OUT_COUPLER, DistributedTags.RMI},
			{DistributedTags.REGISTRY, DistributedTags.RMI},				

	};
	@Override
	public String[][] getClassesTags() {
		// TODO Auto-generated method stub
		return classesTags;
	}

}
