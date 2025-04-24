package Home;

import java.util.*;

public class SmartHomeSystem {
    private List<Device> devices;
    private List<Rule> rules;

    public SmartHomeSystem() {
        this.devices = new ArrayList<>();
        this.rules = new ArrayList<>();
    }

    public String addDevice(String type, String name, String protocol) {
        for (Device device : devices) {
            if (device.getName().equals(name)) {
                return "duplicate device name";
            }
        }

        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth")) {
            return "invalid input";
        }

        Device device;

        if (type.equals("light")) {
            device = new Light(name, protocol);
        } else if (type.equals("thermostat")) {
            device = new Thermostat(name, protocol);
        } else {
            return "invalid input";
        }

        devices.add(device);
        return "device added successfully";
    }


    public String setDevice(String name, String property, String value) {
        Device device = findDeviceByName(name);
        if (device == null) {
            return "device not found";
        }

        if (!device.setProperty(property, value)) {
            if (property.equals("status") || property.equals("brightness") || property.equals("temperature")) {
                return "invalid value";
            }
            return "invalid property";
        }

        return "device updated successfully";
    }

    public String removeDevice(String name) {
        Device device = findDeviceByName(name);
        if (device == null) {
            return "device not found";
        }

        devices.remove(device);
        rules.removeIf(rule -> rule.getDeviceName().equals(name));
        return "device removed successfully";
    }

    public List<String> listDevices() {
        List<String> result = new ArrayList<>();
        if (devices.isEmpty()) {
            result.add("");
            return result;
        }

        for (Device device : devices) {
            result.add(device.getInfo());
        }
        return result;
    }

    public String addRule(String name, String time, String action) {
        if (findDeviceByName(name) == null) {
            return "device not found";
        }

        if (!isValidTime(time)) {
            return "invalid time";
        }

        if (!action.equals("on") && !action.equals("off")) {
            return "invalid action";
        }

        for (Rule rule : rules) {
            if (rule.getDeviceName().equals(name) && rule.getTime().equals(time)) {
                return "duplicate rule";
            }
        }

        rules.add(new Rule(name, time, action));
        return "rule added successfully";
    }

    public String checkRules(String time) {
        if (!isValidTime(time)) {
            return "invalid time";
        }

        for (Rule rule : rules) {
            if (rule.getTime().equals(time)) {
                Device device = findDeviceByName(rule.getDeviceName());
                if (device != null) {
                    device.setProperty("status", rule.getAction());
                }
            }
        }

        return "rules checked";
    }

    public List<String> listRules() {
        List<String> result = new ArrayList<>();
        if (rules.isEmpty()) {
            result.add("");
            return result;
        }

        for (Rule rule : rules) {
            result.add(rule.toString());
        }
        return result;
    }

    private Device findDeviceByName(String name) {
        for (Device device : devices) {
            if (device.getName().equals(name)) {
                return device;
            }
        }
        return null;
    }

    private boolean isValidTime(String time) {
        if (time.length() != 5 || time.charAt(2) != ':') {
            return false;
        }

        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3));

            return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
