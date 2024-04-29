package gradingTools.comp533s24.assignment02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Orders {

    public Orders(List<List<String>> monitorEnterOrders, List<List<String>> monitorExitOrders, List<List<String>> conditionEnterOrders, List<List<String>> conditionExitOrders, List<List<String>> urgentEnterOrders, List<List<String>> urgentExitOrders) {
        this.monitorEnterOrders = monitorEnterOrders;
        this.monitorExitOrders = monitorExitOrders;
        this.conditionEnterOrders = conditionEnterOrders;
        this.conditionExitOrders = conditionExitOrders;
        this.urgentEnterOrders = urgentEnterOrders;
        this.urgentExitOrders = urgentExitOrders;
    }

    private List<List<String>> monitorEnterOrders = new ArrayList<>();
    private List<List<String>> monitorExitOrders = new ArrayList<>();
    private List<List<String>> conditionEnterOrders = new ArrayList<>();
    private List<List<String>> conditionExitOrders = new ArrayList<>();
    private List<List<String>> urgentEnterOrders = new ArrayList<>();
    private List<List<String>> urgentExitOrders = new ArrayList<>();

    public List<List<String>> getMonitorEnterOrders() {
        return monitorEnterOrders;
    }

    public List<List<String>> getMonitorExitOrders() {
        return monitorExitOrders;
    }

    public List<List<String>> getConditionEnterOrders() {
        return conditionEnterOrders;
    }

    public List<List<String>> getConditionExitOrders() {
        return conditionExitOrders;
    }

    public List<List<String>> getUrgentEnterOrders() {
        return urgentEnterOrders;
    }

    public List<List<String>> getUrgentExitOrders() {
        return urgentExitOrders;
    }

    public static Orders extraOrdersFromFile(String fname) throws FileNotFoundException {
    Scanner in = new Scanner(new FileInputStream(fname));

    List<List<String>> monitorEnterOrders = new ArrayList<>();
    List<List<String>> monitorExitOrders = new ArrayList<>();
    List<List<String>> conditionEnterOrders = new ArrayList<>();
    List<List<String>> conditionExitOrders = new ArrayList<>();
    List<List<String>> urgentEnterOrders = new ArrayList<>();
    List<List<String>> urgentExitOrders = new ArrayList<>();


    while (in.hasNext()) {

      String line = in.nextLine();

      // See if we get the actual queue.
      if (line.equals("Start Orders.")) {
        line = in.nextLine(); // Skip  the divider
        monitorEnterOrders.add(createListFromLine(in.nextLine()));
        monitorExitOrders.add(createListFromLine(in.nextLine()));
        conditionEnterOrders.add(createListFromLine(in.nextLine()));
        conditionExitOrders.add(createListFromLine(in.nextLine()));
        urgentEnterOrders.add(createListFromLine(in.nextLine()));
        urgentExitOrders.add(createListFromLine(in.nextLine()));
//        urgentEnterOrders.add(createListFromLine(in.nextLine()));
      }
    }

    return new Orders(monitorEnterOrders, monitorExitOrders, conditionEnterOrders, conditionExitOrders, urgentEnterOrders, urgentExitOrders);
  }

    private static List<String> createListFromLine(String line) {
        String[] nameAndValue = line.split(":");

        String value = nameAndValue[1];
        String[] threadNames = getThreadNames(value);
        return Arrays.asList(threadNames);
    }
    
    public static List<String> getLastEntry(List<List<String>> aList) {
    	return aList.get(aList.size() - 1);
    }
    
    static String[] emptyArray = {};
    private static String[] getThreadNames(String value) {
    	String normalized = value.trim().replace("[", "").replace("]", "");
    	if (normalized.isEmpty()) {
    		return emptyArray;
    	}
    	String[] retVal = normalized.split(",");
    	for (int anIndex = 0; anIndex < retVal.length; anIndex++) {
    		retVal[anIndex] = retVal[anIndex].trim();
    	}
        return  retVal;
//        return value.trim().replace("[", "").replace("]", "").split(",");
    }
}
