package pl.com.healthylifestyle.healthylifestyle.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by szymonidas on 04.01.16.
 */
@Table(name = "meal")
public class Meal extends Model implements Serializable{

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date startMealTime;

    @Column
    private Double caloriesAmount;

    @Column
    private int GI;

    @Column
    private Date reminder;

    @Column
    private int notificationId;

    public Meal(){
        super();
    }

    public Meal(String name, String description, Date startMealTime, Double caloriesAmount, int GI) {
        this.name = name;
        this.description = description;
        this.startMealTime = startMealTime;
        this.caloriesAmount = caloriesAmount;
        this.GI = GI;
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

    public Double getCaloriesAmount() {
        return caloriesAmount;
    }

    public void setCaloriesAmount(Double caloriesAmount) {
        this.caloriesAmount = caloriesAmount;
    }

    public Date getStartMealTime() {
        return startMealTime;
    }

    public void setStartMealTime(Date startMealTime) {
        this.startMealTime = startMealTime;
    }

    public int getGI() {
        return GI;
    }

    public void setGI(int GI) {
        this.GI = GI;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }
}
