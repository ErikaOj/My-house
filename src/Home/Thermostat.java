package Home;

public class Thermostat extends Device {
    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    @Override
    public boolean setProperty(String property, String value) {
        if (property.equals("status")) {
            if (value.equals("on") || value.equals("off")) {
                this.status = value;
                return true;
            }
            return false;
        } else if (property.equals("temperature")) {
            try {
                int temp = Integer.parseInt(value);
                if (temp >= 10 && temp <= 30) {
                    this.temperature = temp;
                    return true;
                }
            } catch (NumberFormatException e) {
                return false;
            }
            return false;
        } else {
            return false;
        }
    }


    @Override
    public String getInfo() {
        return String.format("%s %s %dC %s", name, status, temperature, protocol);
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String getType() {
        return "thermostat";
    }
}