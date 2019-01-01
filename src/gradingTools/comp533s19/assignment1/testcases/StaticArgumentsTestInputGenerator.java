package gradingTools.comp533s19.assignment1.testcases;

import util.pipe.AnAbstractInputGenerator;

public class StaticArgumentsTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private boolean quitSubmitted = false;
	private boolean foundServerInfo = false;
	private boolean foundClientInfo = false;
	
	private String serverInfoLine;
	private String clientInfoLine;
	
	public StaticArgumentsTestInputGenerator() {
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (anOutputLine.startsWith(TRACER_PREFIX) && anOutputLine.contains("SocketChannelBound")) {
				foundServerInfo = true;
				serverInfoLine = anOutputLine;
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (anOutputLine.startsWith(TRACER_PREFIX) && anOutputLine.contains("SocketChannelConnectRequested")) {
				foundClientInfo = true;
				clientInfoLine = anOutputLine;
			}
		}
		if (!quitSubmitted && foundServerInfo && foundClientInfo) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean foundClientInfo() {
		return foundClientInfo;
	}

	public boolean foundServerInfo() {
		return foundServerInfo;
	}
	
	public String getClientInfo() {
		return clientInfoLine;
}
	
	public String getServerInfo() {
		return serverInfoLine;
	}
}
