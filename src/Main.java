import Home.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();

        int q = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < q; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("add_device")) {
                String type = parts[1];
                String name = parts[2];
                String protocol = parts[3];
                System.out.println(system.addDevice(type, name, protocol));
            } else if (command.equals("set_device")) {
                String devName = parts[1];
                String property = parts[2];
                String value = parts[3];
                System.out.println(system.setDevice(devName, property, value));
            } else if (command.equals("remove_device")) {
                String deviceName = parts[1];
                System.out.println(system.removeDevice(deviceName));
            } else if (command.equals("list_devices")) {
                List<String> devices = system.listDevices();
                for (String deviceInfo : devices) {
                    System.out.println(deviceInfo);
                }
            } else if (command.equals("add_rule")) {
                String ruleDevice = parts[1];
                String time = parts[2];
                String action = parts[3];
                System.out.println(system.addRule(ruleDevice, time, action));
            } else if (command.equals("check_rules")) {
                String checkTime = parts[1];
                System.out.println(system.checkRules(checkTime));
            } else if (command.equals("list_rules")) {
                List<String> rules = system.listRules();
                for (String ruleInfo : rules) {
                    System.out.println(ruleInfo);
                }
            }
        }

        scanner.close();
    }
}
