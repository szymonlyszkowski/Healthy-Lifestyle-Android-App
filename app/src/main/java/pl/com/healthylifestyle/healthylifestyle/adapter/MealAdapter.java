package pl.com.healthylifestyle.healthylifestyle.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;

import java.util.List;

public class MealAdapter extends ArrayAdapter<Meal> {

    private List<Meal> mealsList;
    private Context context;

    public MealAdapter(Context context, int resource, List<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mealsList = objects;
    }
}
