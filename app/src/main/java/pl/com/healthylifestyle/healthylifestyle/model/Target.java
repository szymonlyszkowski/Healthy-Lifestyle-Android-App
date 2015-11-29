package pl.com.healthylifestyle.healthylifestyle.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * @author alisowsk
 */
@Table(name = "target")
public class Target extends Model {

    @Column
    public String name;

    @Column
    public double startValue;

    @Column
    public double desiredValue;

    @Column
    public double currentValue;

    public Target(){
        super();
    }

    public Target(String name, double startValue, double desiredValue) {
        super();
        this.name = name;
        this.startValue = startValue;
        this.currentValue = startValue;
        this.desiredValue = desiredValue;
    }

    public Target(String name, double startValue, double desiredValue, double currentValue) {
        super();
        this.name = name;
        this.startValue = startValue;
        this.desiredValue = desiredValue;
        this.currentValue = currentValue;
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

