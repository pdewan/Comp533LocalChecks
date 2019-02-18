package gradingTools.comp533s19.assignment3.testcases;

import java.util.Arrays;
import java.util.regex.Pattern;

import gradingTools.comp533s19.assignment3.testcases.FlexibleTwoClientCorrectConnectionTestInputGenerator;
import util.trace.Tracer;

public class MetaStateBroadcastTestInputGenerator extends FlexibleTwoClientCorrectConnectionTestInputGenerator {
	private static final boolean PRINT_CHECKED_REGEX = true;
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	
	private static final String NIO = "ipc_mechanism,-1.0=NIO";
	private static final String RMI = "ipc_mechanism,-1.0=RMI";
	private static final String GIPC = "ipc_mechanism,-1.0=GIPC";
	
	private static final Pattern META_STATE_BROADCAST_NOT_IMPLEMENTED = Pattern.compile("broadcastMetaState called with argument '\".*?\"' but it has not been implemented.");
	private static final Pattern SET_IPC_NOT_IMPLEMENTED = Pattern.compile("ipcMechanism called with argument '\".*?\"' but it has not been implemented.");

	private boolean[] foundNIO = new boolean[]{false, false, false};
	private boolean[] foundRMI = new boolean[]{false, false, false};
	private boolean[] foundGIPC = new boolean[]{false, false, false};
	
	private boolean[] clientImplemented = new boolean[] {true, true, true, true};
	private boolean[] serverImplemented = new boolean[] {true, true, true, true};
	
	private static final boolean[] CORRECT_BROADCAST = new boolean[] {true, true, true};
	private static final boolean[] CORRECT_NON_BROADCAST_FROM_CLIENT = new boolean[] {false, true, false};
	private static final boolean[] CORRECT_NON_BROADCAST_FROM_SERVER = new boolean[] {true, false, false};
	
	private boolean broadcast;
	private boolean clientIsSource;
	private boolean setupServer = false;
	private boolean setupClient0 = false;
	private boolean setupClient1 = false;
	private boolean quitSubmitted = false;
	private boolean allConnected = false;
	
	private boolean doneNIO = false;
	private boolean doneRMI = false;
	private boolean doneGIPC = false;
	
	public MetaStateBroadcastTestInputGenerator(boolean broadcast, boolean clientIsSource, boolean doNIO, boolean doRMI, boolean doGIPC) {
		super(doNIO, doRMI, doGIPC);
		this.broadcast = broadcast;
		this.clientIsSource = clientIsSource;
	}

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		boolean didServer = false;
		boolean didClient0 = false;
		boolean didClient1 = false;
		
		if (setupServer == false && SERVER_NAME.equals(aProcessName)) {
			notifyNewInputLine(SERVER_NAME, "b " + broadcast);
			setupServer = true;
		}
		if (setupClient0 == false && CLIENT_0_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_0_NAME, "b " + broadcast);
			setupClient0 = true;
		}

		if (setupClient1 == false && CLIENT_1_NAME.equals(aProcessName)) {
			notifyNewInputLine(CLIENT_1_NAME, "b " + broadcast);
			setupClient1 = true;
		}
		// use lines for connection first
		if (aProcessName.equals(SERVER_NAME)) {
			didServer = checkServer(anOutputLine);
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			didClient0 = checkClient0(anOutputLine);
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			didClient1 = checkClient1(anOutputLine);
		}
		if (!allConnected && (didServer || didClient0 || didClient1) && areServerAcceptsComplete() && areClientConnectsComplete()) {
			allConnected = true;
			String source = clientIsSource ? CLIENT_0_NAME : SERVER_NAME;
			if (doNIO() && !doneNIO) {
				notifyNewInputLine(source, "i nio");
				doneNIO = true;
			} else if (doRMI() && !doneRMI) {
				notifyNewInputLine(source, "i rmi");
				doneRMI = true;
			} else if (doGIPC() && !doneGIPC) {
				notifyNewInputLine(source, "i gipc");
				doneGIPC = true;
			}
				
		}
		// read/write if not connect
		if (allConnected) {
			if (!didServer && aProcessName.equals(SERVER_NAME)) {
				if (!clientIsSource) {
					if (doRMI() && !doneRMI) {
						notifyNewInputLine(SERVER_NAME, "i rmi");
						doneRMI = true;
					} else if (doGIPC() && !doneGIPC) {
						notifyNewInputLine(SERVER_NAME, "i gipc");
						doneGIPC = true;
					}
				}
				if (checkCanSetMetaBroadcast(anOutputLine, serverImplemented)) {
				} else if (checkNIO(anOutputLine, serverImplemented, 0)) {
				} else if (checkRMI(anOutputLine, serverImplemented, 0)) {
				} else if (checkGIPC(anOutputLine, serverImplemented, 0)) {
				}
			} else if (!didClient0 && aProcessName.equals(CLIENT_0_NAME)) {
				if (clientIsSource) {
					if (doRMI() && !doneRMI) {
						notifyNewInputLine(CLIENT_0_NAME, "i rmi");
						doneRMI = true;
					} else if (doGIPC() && !doneGIPC) {
						notifyNewInputLine(CLIENT_0_NAME, "i gipc");
						doneGIPC = true;
					}
				}
				if (checkCanSetMetaBroadcast(anOutputLine, clientImplemented)) {
				} else if (checkNIO(anOutputLine, clientImplemented, 1)) {
				} else if (checkRMI(anOutputLine, clientImplemented, 1)) {
				} else if (checkGIPC(anOutputLine, clientImplemented, 1)) {
				}
			} else if (!didClient1 && aProcessName.equals(CLIENT_1_NAME)) {
				if (checkCanSetMetaBroadcast(anOutputLine, clientImplemented)) {
				} else if (checkNIO(anOutputLine, clientImplemented, 2)) {
				} else if (checkRMI(anOutputLine, clientImplemented, 2)) {
				} else if (checkGIPC(anOutputLine, clientImplemented, 2)) {
				}
			}
		}
		if (!quitSubmitted && (!doNIO() || isNIOCorrect()) && (!doRMI() || isRMICorrect()) && (!doGIPC() || isGIPCCorrect())) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	private boolean checkIfMatches(String line, Pattern pattern) {
		if (PRINT_CHECKED_REGEX) {
			Tracer.info(this, "Checking for line matching: " + pattern.pattern());
		}
		return (pattern.matcher(line).matches());
	}
	
	private boolean checkCanSetMetaBroadcast(String line, boolean[] implemented) {
		if (checkIfMatches(line, META_STATE_BROADCAST_NOT_IMPLEMENTED)) {
			implemented[0] = false;
			return true;
		}
		return false;
	}
	
	private boolean checkNIO(String line, boolean[] implemented, int p) {
		if (checkIfMatches(line, SET_IPC_NOT_IMPLEMENTED)) {
			implemented[1] = false;
			return true;
		} else if (implemented[1]) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line containing: " + NIO);
			}
			if (line.contains(NIO)) {
				foundNIO[p] = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRMI(String line, boolean[] implemented, int p) {
		if (checkIfMatches(line, SET_IPC_NOT_IMPLEMENTED)) {
			implemented[2] = false;
			return true;
		} else if (implemented[2]) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line containing: " + RMI);
			}
			if (line.contains(RMI)) {
				foundRMI[p] = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkGIPC(String line, boolean[] implemented, int p) {
		if (checkIfMatches(line, SET_IPC_NOT_IMPLEMENTED)) {
			implemented[2] = false;
			return true;
		} else if (implemented[2]) {
			if (PRINT_CHECKED_REGEX) {
				Tracer.info(this, "Checking for line containing: " + GIPC);
			}
			if (line.contains(GIPC)) {
				foundGIPC[p] = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean isNIOCorrect() {
		if (broadcast) {
			return Arrays.equals(CORRECT_BROADCAST, foundNIO);
		} else {
			if (clientIsSource) {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_CLIENT, foundNIO);
			} else {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_SERVER, foundNIO);
			}
		}
	}
	
	public boolean isRMICorrect() {
		if (broadcast) {
			return Arrays.equals(CORRECT_BROADCAST, foundRMI);
		} else {
			if (clientIsSource) {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_CLIENT, foundRMI);
			} else {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_SERVER, foundRMI);
			}
		}
	}
	
	public boolean isGIPCCorrect() {
		if (broadcast) {
			return Arrays.equals(CORRECT_BROADCAST, foundGIPC);
		} else {
			if (clientIsSource) {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_CLIENT, foundGIPC);
			} else {
				return Arrays.equals(CORRECT_NON_BROADCAST_FROM_SERVER, foundGIPC);
			}
		}
	}
	
	private static String observedToMessage(boolean[] observed, boolean[] expected, boolean clientIsSource) {
		StringBuilder message = new StringBuilder();
		if (observed[0]) {
			if (!expected[0]) {
				message.append("Server recieved unexpected notification");
			}
		} else {
			if (!clientIsSource) {
				message.append("Source server did not print state chagne");
			} else if (expected[0]) {
				message.append("Server did not recieve expected notification");
			}
		}
		if (observed[1]) {
			if (!expected[1]) {
				if (message.length() > 0) {
					message.append(", ");
				}
				message.append("Client 0 recieved unexpected notification");
			}
		} else {
			if (clientIsSource) {
				if (message.length() > 0) {
					message.append(", ");
				}
				message.append("Source client did not print state change");
			} else if (expected[1]) {
				if (message.length() > 0) {
					message.append(", ");
				}
				message.append("Client 0 did not recieve expected notification");
			}
		}
		if (observed[2]) {
			if (!expected[2]) {
				message.append("Client 1 recieved unexpected notification");
			}
		} else {
			if (expected[2]) {
				if (message.length() > 0) {
					message.append(", ");
				}
				message.append("Client 1 did not recieve expected notification");
			}
		}
		return message.toString();
	}
	
	public String getErrorMessage() {
		if (!areServerAcceptsComplete() || !areClientConnectsComplete() || !isServerSetupComplete()) {
			return "In " + getLastNotFoundSource() + ", no line found matching regex: " + getLastNotFound();
		}
		StringBuilder message = new StringBuilder();
		boolean clientError = false;
		boolean serverError = false;
		if (!clientImplemented[0]) {
			clientError = true;
			message.append("Client does not implement broadcastMetaState(boolean)");
		}
		if (!clientImplemented[1] || !clientImplemented[2] || !clientImplemented[3]) {
			if (clientError) {
				message.append(", ");
			} else {
				message.append("Client does not implement ");
			}
			clientError = true;
			message.append("ipcMechanism(IPCMechanism)");
		}
		if (!serverImplemented[0]) {
			serverError = true;
			if (clientError == true) {
				message.append(";\n");
			}
			message.append("Client does not implement broadcastMetaState(boolean)");
		}
		if (!serverImplemented[1] || !serverImplemented[2] || !serverImplemented[3]) {
			if (serverError) {
				message.append(", ");
			} else {
				if (clientError == true) {
					message.append(";\n");
				}
				message.append("Client does not implement ");
			}
			serverError = true;
			message.append("ipcMechanism(IPCMechanism)");
		}
		if (doNIO()) {
			StringBuilder nioMessage = new StringBuilder();
			if (broadcast) {
				nioMessage.append(observedToMessage(foundNIO, CORRECT_BROADCAST, clientIsSource));
			} else {
				if (clientIsSource) {
					nioMessage.append(observedToMessage(foundNIO, CORRECT_NON_BROADCAST_FROM_CLIENT, clientIsSource));
				} else {
					nioMessage.append(observedToMessage(foundNIO, CORRECT_NON_BROADCAST_FROM_SERVER, clientIsSource));
				}
			}
			if (nioMessage.length() > 0) {
				if (message.length() > 0) {
					message.append(";\n");
				}
				message.append("When giving 'i nio' as input to ").append(clientIsSource ? "Client 0: " : "Server: ").append(nioMessage);
			}
		}
		if (doRMI()) {
			StringBuilder rmiMessage = new StringBuilder();
			if (broadcast) {
				rmiMessage.append(observedToMessage(foundRMI, CORRECT_BROADCAST, clientIsSource));
			} else {
				if (clientIsSource) {
					rmiMessage.append(observedToMessage(foundRMI, CORRECT_NON_BROADCAST_FROM_CLIENT, clientIsSource));
				} else {
					rmiMessage.append(observedToMessage(foundRMI, CORRECT_NON_BROADCAST_FROM_SERVER, clientIsSource));
				}
			}
			if (rmiMessage.length() > 0) {
				if (message.length() > 0) {
					message.append(";\n");
				}
				message.append("When giving 'i rmi' as input to ").append(clientIsSource ? "Client 0: " : "Server: ").append(rmiMessage);
			}
		}
		if (doGIPC()) {
			StringBuilder gipcMessage = new StringBuilder();
			if (broadcast) {
				gipcMessage.append(observedToMessage(foundGIPC, CORRECT_BROADCAST, clientIsSource));
			} else {
				if (clientIsSource) {
					gipcMessage.append(observedToMessage(foundGIPC, CORRECT_NON_BROADCAST_FROM_CLIENT, clientIsSource));
				} else {
					gipcMessage.append(observedToMessage(foundGIPC, CORRECT_NON_BROADCAST_FROM_SERVER, clientIsSource));
				}
			}
			if (gipcMessage.length() > 0) {
				if (message.length() > 0) {
					message.append(";\n");
				}
				message.append("When giving 'i gipc' as input to ").append(clientIsSource ? "Client 0: " : "Server: ").append(gipcMessage);
			}
		}
		return message.toString();
	}
}
