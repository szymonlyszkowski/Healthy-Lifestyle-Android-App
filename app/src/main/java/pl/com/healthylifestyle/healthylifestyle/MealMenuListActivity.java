package pl.com.healthylifestyle.healthylifestyle;

import android.app.ListActivity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.activeandroid.query.Select;
import pl.com.healthylifestyle.healthylifestyle.adapter.MealAdapter;
import pl.com.healthylifestyle.healthylifestyle.adapter.TargetAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;
import pl.com.healthylifestyle.healthylifestyle.model.Target;

import java.util.Date;
import java.util.List;

public class MealMenuListActivity extends ListActivity {

    private List mealsList;
    private MealAdapter mealsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu_layout);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.healty_green));
        initFields();
    }

    private void initFields() {
        this.mealsList = new Select().from(Meal.class).execute();
        prepareTestData(); //TODO remove

        this.mealsListAdapter = new MealAdapter(this, android.R.layout.simple_list_item_1, mealsList);
        setListAdapter(mealsListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items
        // to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareTestData(){
        if(mealsList.size() == 0){
            new Meal("Meal 1", "Description of meal 1 ",new Date(),100.2, 10 ).save();
            new Meal("Meal 2", "Description of meal 2 ",new Date(),200.0, 10 ).save();
            new Meal("Meal 3", "Description of meal 3 ",new Date(),300.50, 10 ).save();
            new Meal("Meal 4", "Description of meal 4 ",new Date(),4000.10, 10 ).save();
            new Meal("Meal 5", "Description of meal 5 ",new Date(),50.50, 10 ).save();
            mealsList = new Select().from(Target.class).execute();
        }
    }

}
