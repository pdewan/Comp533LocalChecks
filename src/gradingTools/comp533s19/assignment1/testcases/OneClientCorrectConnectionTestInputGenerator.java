package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class OneClientCorrectConnectionTestInputGenerator extends AnAbstractInputGenerator {
private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	private int enableAcceptStage = 0;
	private int connectStage = 0;
	private boolean hasPropertyChangeListener = false;
	private boolean hasReadListener = false;
	private int acceptStage = 0;
	
	private boolean quitSubmitted = false;

	private static final Pattern[] enableAcceptStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelBound"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "ListenableAcceptsEnabled"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "ListenableAcceptsEnabled"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] connectStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
//			checkStr(MAIN_THREAD, "AddedPropertyChangeListener"),
			
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern readListenerPattern = multipleCheckStr(MAIN_THREAD, "ReadListenerAdded", SELECT_THREAD, "ReadListenerAdded");
	private static final Pattern propertyChangeListenerPattern = checkStr(MAIN_THREAD, "AddedPropertyChangeListener");
	
	private static final Pattern[] acceptStages = {
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			multipleCheckStr(SELECT_THREAD, "SocketChannelRegistered", SELECT_THREAD, "ReadListenerAdded"),
			multipleCheckStr(SELECT_THREAD, "SocketChannelRegistered", SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	public OneClientCorrectConnectionTestInputGenerator() {
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isEnableAcceptComplete()) {
				checkEnableAccept(anOutputLine);
			} else if (canProcessAccept() && !isAcceptComplete()) {
				checkAccept(anOutputLine);
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (isEnableAcceptComplete() && !isConnectComplete()) {
				if(checkForPropertyChangeListener(anOutputLine)) {
				} else if (checkForReadListener(anOutputLine)) {
				} else if (!isConnectFSMComplete()) {
					checkConnect(anOutputLine);
				}
			}
		}
		if (!quitSubmitted && isAcceptComplete()) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isEnableAcceptComplete() {
		return enableAcceptStage == enableAcceptStages.length;
	}
	
	public boolean isConnectFSMComplete() {
		return connectStage == connectStages.length;
	}
	
	public boolean isConnectComplete() {
		return connectStage == connectStages.length && hasReadListener && hasPropertyChangeListener;
	}
	

	public boolean canProcessAccept() {
		return connectStage >= 7;
	}
	
	public boolean isAcceptComplete() {
		return acceptStage == acceptStages.length;
	}
	
	public boolean checkEnableAccept(String line) {
		Tracer.info(this, "Checking for line matching: " + enableAcceptStages[enableAcceptStage]);
		if (line.startsWith(TRACER_PREFIX) && enableAcceptStages[enableAcceptStage].matcher(line).matches()) {
			enableAcceptStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkConnect(String line) {
		if (line.startsWith(TRACER_PREFIX) && connectStages[connectStage].matcher(line).matches()) {
			connectStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkForReadListener(String line) {
		if (line.startsWith(TRACER_PREFIX) && readListenerPattern.matcher(line).matches()) {
			hasReadListener = true;
			return true;
		}
		return false;
	}
	
	public boolean checkForPropertyChangeListener(String line) {
		if (line.startsWith(TRACER_PREFIX) && propertyChangeListenerPattern.matcher(line).matches()) {
			hasPropertyChangeListener = true;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[acceptStage].matcher(line).matches()) {
			acceptStage++;
			return true;
		}
		return false;
	}
	
	public String getLastNotFoundSource() {
		if (!isEnableAcceptComplete()) {
			return "Server enabling accepts";
		} else if (!isConnectComplete()) {
			return "Client connecting";
		} else if (!isAcceptComplete()) {
			return "Server accepting client's connection";
		} else {
			return "";
		}
	}
	
	public String getLastNotFound() {
		if (!isEnableAcceptComplete()) {
			return enableAcceptStages[enableAcceptStage].pattern();
		} else if (!isConnectComplete()) {
			if (connectStage != connectStages.length) {
				return connectStages[connectStage].pattern();
			} else if (!hasPropertyChangeListener) {
				return propertyChangeListenerPattern.pattern();
			} else {
				return readListenerPattern.pattern();
			}
		} else if (!isAcceptComplete()) {
			return acceptStages[acceptStage].pattern();
		} else {
			return "";
		}
	}
}
