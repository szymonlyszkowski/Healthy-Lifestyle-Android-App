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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MealAdapter extends ArrayAdapter<Meal> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private List<Meal> mealsList;
    private Context context;

    public MealAdapter(Context context, int resource, List<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mealsList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MealItemHolder mealItemHolder;
        View view = convertView;
        if (view == null) {
            mealItemHolder = new MealItemHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.activity_meal_custom_row, parent, false);
            mealItemHolder.name = (TextView) view.findViewById(R.id.meal_custom_row);
            view.setTag(mealItemHolder);
        } else {
            mealItemHolder = (MealItemHolder) view.getTag();
        }

        Meal eItem = this.mealsList.get(position);
        String mealName = eItem.getName();
        Double calories = eItem.getCaloriesAmount();
        int gi = eItem.getGI();
        Date startDate = eItem.getStartMealTime();
        StringBuilder rowText = new StringBuilder().append("Meal: ").append(mealName).append("\n").append("Calories: ").append(calories).append("\n")
                .append("GI: ").append(gi)
                .append("\n").append("Start date: ").append(DATE_FORMAT.format(startDate)).append("\n").append("Meal Description: ").append(eItem.getDescription())
                .append("\n").append("Reminder: ");
        if(null != eItem.getReminder()) {
            rowText.append(DATE_FORMAT.format(eItem.getReminder()));
        } else {
            rowText.append(" - ");
        }
        mealItemHolder.name.setText(rowText);

        return view;
    }

    private static class MealItemHolder {
        private TextView name;
    }

}
