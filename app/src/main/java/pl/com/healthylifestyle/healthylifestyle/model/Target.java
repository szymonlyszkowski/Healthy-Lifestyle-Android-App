package pl.com.healthylifestyle.healthylifestyle.model;

/**
 * @author alisowsk
 */
public class Target {

    private Long id;

    private String name;

    private double startValue;

    private double desiredValue;

    private double currentValue;

    public Target(String name, double desiredValue, double startValue) {
        this.name = name;
        this.desiredValue = desiredValue;
        this.startValue = startValue;
        this.currentValue = startValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public double getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(double desiredValue) {
        this.desiredValue = desiredValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
