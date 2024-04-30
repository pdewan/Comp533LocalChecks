package gradingTools.comp533.flexible;

public class PortNumbers {
	private static int PORT_RANGE = 500;
	private static  int testPortNIOStart = 5000;
	private static  int testPortNIOEnd = 6000;
	private static  int testPorRMIStart = 6000;
	private static  int testPortRMIEnd = 7000;
	private static  int testPortGIPCStart = 7000;
	private static  int testPortGIPCEnd = 8000;
	public static int getTestPortNIOStart() {
		return testPortNIOStart;
	}
	public static void setTestPortNIOStart(int testPortNIOStart) {
		PortNumbers.testPortNIOStart = testPortNIOStart;
		PortNumbers.testPortNIOEnd = PortNumbers.testPortNIOStart + PORT_RANGE;
	}
	public static int getTestPortNIOEnd() {
		return testPortNIOEnd;
	}
	public static void setTestPortNIOEnd(int testPortNIOEnd) {
		PortNumbers.testPortNIOEnd = testPortNIOEnd;
	}
	public static int getTestPortRMIStart() {
		return testPorRMIStart;
	}
	public static void setTestPortRMIStart(int testPorRMIStart) {
		PortNumbers.testPorRMIStart = testPorRMIStart;
		PortNumbers.testPortRMIEnd = testPorRMIStart + PORT_RANGE;

	}
	public static int getTestPortRMIEnd() {
		return testPortRMIEnd;
	}
	public static void setTestPortRMIEnd(int testPorRMIEnd) {
		PortNumbers.testPortRMIEnd = testPorRMIEnd;
	}
	public static int getTestPortGIPCStart() {
		return testPortGIPCStart;
	}
	public static void setTestPortGIPCStart(int testPortGIPCStart) {
		PortNumbers.testPortGIPCStart = testPortGIPCStart;
		PortNumbers.testPortGIPCEnd = testPortGIPCStart + PORT_RANGE;

	}
	public static int getTestPortGIPCEnd() {
		return testPortGIPCEnd;
	}
	public static void setTestPortGIPCEnd(int testPortGIPCEnd) {
		PortNumbers.testPortGIPCEnd = testPortGIPCEnd;
	}
	
	

}
