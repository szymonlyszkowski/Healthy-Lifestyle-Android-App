package pl.com.healthylifestyle.healthylifestyle.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by szymonidas on 04.01.16.
 */
@Table(name = "target")
public class Meal extends Model implements Serializable{


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meal(){
        super();
    }

    public Meal(String name, String description, Date startMealTime, Double caloriesAmount, int GI) {
        this.name = name;
        Description = description;
        this.startMealTime = startMealTime;
        this.caloriesAmount = caloriesAmount;
        this.GI = GI;
    }

    @Column
    private String name;
    @Column
    private String Description;
    @Column
    private Date startMealTime;
    @Column
    private Double caloriesAmount;
    @Column
    private int GI;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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





}
