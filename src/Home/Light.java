package Home;

public class Light extends Device {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    @Override
    public boolean setProperty(String property, String value) {
        if (property.equals("status")) {
            if (value.equals("on") || value.equals("off")) {
                this.status = value;
                return true;
            }
            return false;
        } else if (property.equals("brightness")) {
            try {
                int brightness = Integer.parseInt(value);
                if (brightness >= 0 && brightness <= 100) {
                    this.brightness = brightness;
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
        return String.format("%s %s %d%% %s", name, status, brightness, protocol);
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public String getType() {
        return "light";
    }
}