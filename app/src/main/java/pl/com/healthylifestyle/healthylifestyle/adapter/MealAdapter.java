package pl.com.healthylifestyle.healthylifestyle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.com.healthylifestyle.healthylifestyle.R;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;
import pl.com.healthylifestyle.healthylifestyle.model.Target;

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
