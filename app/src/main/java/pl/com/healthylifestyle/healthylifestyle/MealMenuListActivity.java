package pl.com.healthylifestyle.healthylifestyle;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import com.activeandroid.query.Select;
import pl.com.healthylifestyle.healthylifestyle.adapter.MealAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MealMenuListActivity extends ListActivity {

    private List<Meal> mealsList = new ArrayList<>();;
    private MealAdapter mealsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu_layout);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.healty_green));
        getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        mealsList = new Select().from(Meal.class).execute();
        prepareTestData();
        initFields();
        createMealRemoval();
        createMealAddition();

    }

    private void initFields() {
//        prepareTestData(); //TODO remove
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
            mealsList = new Select().from(Meal.class).execute();
        }
    }



    private void createMealRemoval(){
        /** Defining a click event listener for the button "Delete" */
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        mealsListAdapter.remove(mealsList.get(i));
                    }
                }
                checkedItemPositions.clear();
                mealsListAdapter.notifyDataSetChanged();
            }
        };
        Button btnDel = (Button) findViewById(R.id.remove_meal_button);
        btnDel.setOnClickListener(listenerDel);
    }


    private void createMealAddition(){
         View.OnClickListener listenerAddMeal = new View.OnClickListener(){
             @Override
         public void onClick(View v){

                 displayAddMealActivity();
             }
         };
        Button buttonAddMeal = (Button) findViewById(R.id.add_meal_menu_button);
        buttonAddMeal.setOnClickListener(listenerAddMeal);
    }

    private void displayAddMealActivity(){
        Intent intent = new Intent(this, MealAddActivity.class);
        this.startActivity(intent);
    }



}
