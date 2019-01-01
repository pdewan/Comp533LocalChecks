package gradingTools.comp533s19.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class TwoClientCorrectConnectionTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	protected int enableAcceptStage = 0;
	protected int connect0Stage = 0;
	protected int connect1Stage = 0;
	private boolean hasReadListener0 = false;
	private boolean hasReadListener1 = false;
	private boolean hasPropertyChangeListener0 = false;
	private boolean hasPropertyChangeListener1 = false;
	protected int accept0Stage = 0;
	protected int accept1Stage = 0;
	
	private boolean quitSubmitted = false;

	protected static final Pattern[] enableAcceptStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelBound"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SelectorRequestNextInterestOp"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SelectorRequestNextInterestOp"),
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
	
	// SelectCalled seems to vary some
	protected static final Pattern[] connectStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
//			checkStr(MAIN_THREAD, "AddedPropertyChangeListener"),
//			checkStr(MAIN_THREAD, "AddedPropertyChangeListener"),
//			checkStr(MAIN_THREAD, "ReadListenerAdded"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SocketChannelConnectRequested"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SocketChannelConnectRequested"),
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
//			checkStr(SELECT_THREAD, "SelectCalled"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern readListenerPattern = multipleCheckStr(MAIN_THREAD, "ReadListenerAdded", SELECT_THREAD, "ReadListenerAdded");
	private static final Pattern propertyChangeListenerPattern = checkStr(MAIN_THREAD, "AddedPropertyChangeListener");

	protected static final Pattern[] acceptStages = {
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			multipleCheckStr(SELECT_THREAD, "SocketChannelRegistered", SELECT_THREAD, "ReadListenerAdded"),
			multipleCheckStr(SELECT_THREAD, "SocketChannelRegistered", SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	
	public TwoClientCorrectConnectionTestInputGenerator() {
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isEnableAcceptComplete()) {
				checkEnableAccept(anOutputLine);
			} else if (canProcessAccept()) {
				// each output can only be from 1 accept
				boolean accepted = false;
				if (!isAccepted0Complete()) {
					accepted = checkAccept0(anOutputLine);
				}
				if (!accepted && !isAccepted1Complete()) {
					checkAccept1(anOutputLine);
				}
			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			if (isEnableAcceptComplete() && !isConnect0Complete()) {
				if (checkForPropertyChangeListener0(anOutputLine)) {
				} else if (checkForReadListener0(anOutputLine)) {
				} else if (!isConnect0FSMComplete()){
					checkConnect0(anOutputLine);
				}
			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			if (isEnableAcceptComplete() && !isConnect1Complete()) {
				if (checkForPropertyChangeListener1(anOutputLine)) {
				} else if (checkForReadListener1(anOutputLine)) {
				} else if (!isConnect1FSMComplete()){
					checkConnect1(anOutputLine);
				}
			}
		}
		if (!quitSubmitted && areAcceptsComplete()) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isEnableAcceptComplete() {
		return enableAcceptStage == enableAcceptStages.length;
	}

	public boolean isConnect0FSMComplete() {
		return connect0Stage == connectStages.length;
	}

	public boolean isConnect1FSMComplete() {
		return connect1Stage == connectStages.length;
	}
	
	public boolean isConnect0Complete() {
		return connect0Stage == connectStages.length && hasReadListener0 && hasPropertyChangeListener0;
	}
	
	public boolean isConnect1Complete() {
		return connect1Stage == connectStages.length && hasReadListener1 && hasPropertyChangeListener1;
	}
	
	public boolean areConnectsComplete() {
		return isConnect0Complete() && isConnect1Complete();
	}
	

	public boolean canProcessAccept() {
		return connect0Stage >= 7 || connect1Stage >= 7;
	}
	
	public boolean isAccepted0Complete() {
		return accept0Stage == acceptStages.length;
	}
	
	public boolean isAccepted1Complete() {
		return accept1Stage == acceptStages.length;
	}
	
	public boolean areAcceptsComplete() {
		return isAccepted0Complete() && isAccepted1Complete();
	}
	
	public boolean checkEnableAccept(String line) {
		if (line.startsWith(TRACER_PREFIX) && enableAcceptStages[enableAcceptStage].matcher(line).matches()) {
			enableAcceptStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkConnect0(String line) {
		if (line.startsWith(TRACER_PREFIX) && connectStages[connect0Stage].matcher(line).matches()) {
			connect0Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkConnect1(String line) {
		if (line.startsWith(TRACER_PREFIX) && connectStages[connect1Stage].matcher(line).matches()) {
			connect1Stage++;
			return true;
		}
		return false;
	}

	public boolean checkForPropertyChangeListener0(String line) {
		if (line.startsWith(TRACER_PREFIX) && propertyChangeListenerPattern.matcher(line).matches()) {
			hasPropertyChangeListener0 = true;
			return true;
		}
		return false;
	}

	public boolean checkForPropertyChangeListener1(String line) {
		if (line.startsWith(TRACER_PREFIX) && propertyChangeListenerPattern.matcher(line).matches()) {
			hasPropertyChangeListener1 = true;
			return true;
		}
		return false;
	}
	
	public boolean checkForReadListener0(String line) {
		if (line.startsWith(TRACER_PREFIX) && readListenerPattern.matcher(line).matches()) {
			hasReadListener0 = true;
			return true;
		}
		return false;
	}
	
	public boolean checkForReadListener1(String line) {
		if (line.startsWith(TRACER_PREFIX) && readListenerPattern.matcher(line).matches()) {
			hasReadListener1 = true;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept0(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept0Stage].matcher(line).matches()) {
			accept0Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept1(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept1Stage].matcher(line).matches()) {
			accept1Stage++;
			return true;
		}
		return false;
	}
	
	public String getLastNotFoundSource() {
		if (!isEnableAcceptComplete()) {
			return "Server enabling accepts";
		} else if (!isConnect0Complete()) {
			return "Client 0 connecting";
		} else if (!isConnect1Complete()) {
			return "Client 1 connecting";
		} else if (!isAccepted0Complete()) {
			return "Server accepting client 0's connection";
		} else if (!isAccepted1Complete()) {
			return "Server accepting client 1's connection";
		} else {
			return "";
		}
	}
	
	public String getLastNotFound() {
		if (!isEnableAcceptComplete()) {
			return enableAcceptStages[enableAcceptStage].pattern();
		} else if (!isConnect0Complete()) {
			if (connect0Stage != connectStages.length) {
				return connectStages[connect0Stage].pattern();
			} else if (!hasPropertyChangeListener0) {
				return propertyChangeListenerPattern.pattern();
			} else {
				return readListenerPattern.pattern();
			}
		} else if (!isConnect1Complete()) {
			if (connect1Stage != connectStages.length) {
				return connectStages[connect1Stage].pattern();
			} else if (!hasPropertyChangeListener1) {
				return propertyChangeListenerPattern.pattern();
			} else {
				return readListenerPattern.pattern();
			}
		} else if (!isAccepted0Complete()) {
			return acceptStages[accept0Stage].pattern();
		} else if (!isAccepted1Complete()) {
			return acceptStages[accept1Stage].pattern();
		} else {
			return "";
		}
	}
}
