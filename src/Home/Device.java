package Home;

public abstract class Device {
    protected String name;
    protected String status;
    protected String protocol;

    public Device(String name, String protocol) {
        this.name = name;
        this.status = "off";
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("on") || status.equals("off")) {
            this.status = status;
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public abstract String getType();

    public abstract String getInfo();

    public abstract boolean setProperty(String property, String value);
}
