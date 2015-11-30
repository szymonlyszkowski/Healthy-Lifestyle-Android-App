package pl.com.healthylifestyle.healthylifestyle.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Calendar;
import java.util.Date;

/**
 * @author alisowsk
 */
@Table(name = "target")
public class Target extends Model {

    @Column
    public String name;

    @Column
    public String description;

    @Column
    public double startValue;

    @Column
    public double desiredValue;

    @Column
    public double currentValue;

    @Column
    public Date startDate;

    @Column
    public Date endDate;

    public Target(){
        super();
    }

    public Target(String name, String description, double startValue, double desiredValue) {
        super();
        this.name = name;
        this.description = description;
        this.startValue = startValue;
        this.currentValue = startValue;
        this.desiredValue = desiredValue;
        this.startDate = Calendar.getInstance().getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

