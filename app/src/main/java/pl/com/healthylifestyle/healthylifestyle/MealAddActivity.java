package pl.com.healthylifestyle.healthylifestyle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;

import java.util.Date;

public class MealAddActivity extends ActionBarActivity {

    private EditText mealNameEditText;
    private EditText mealCaloriesEditText;
    private EditText mealDescriptionEditText;
    private EditText mealGIEditText;
    private EditText mealStartDate;
    private Meal myMeal;
    private boolean newMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_add_new);
        prepareMealObject();
        init_meal_addition_fields();
        //setSaveMealButtonListener();
    }

    private void prepareMealObject(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getSerializable("meal") != null) {
            myMeal = (Meal) bundle.getSerializable("meal");
        }
        if (myMeal == null) {
            myMeal = new Meal();
            newMeal = true;
        } else {
            newMeal = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meal_add, menu);
        return true;
    }

    private void saveMeal() {
        String name = mealNameEditText.getText().toString();
        String description = mealDescriptionEditText.getText().toString();
        int GI = Integer.parseInt(mealGIEditText.getText().toString());
        Date startDate = new Date();
        Double calories = Double.parseDouble(mealCaloriesEditText.getText().toString());
        if(null != mealDescriptionEditText.getText() && mealDescriptionEditText.getText().toString().isEmpty()){
            description = mealDescriptionEditText.getText().toString();
        }


        myMeal.setName(name);
        myMeal.setDescription(description);
        myMeal.setGI(GI);
        myMeal.setDescription(description);
        myMeal.setCaloriesAmount(calories);
        myMeal.setStartMealTime(startDate);
        myMeal.save();
    }

    private void init_meal_addition_fields() {
        mealNameEditText = (EditText) findViewById(R.id.meal_name_edit_text);
        mealCaloriesEditText = (EditText) findViewById(R.id.meal_calories_edit_text);
        mealDescriptionEditText = (EditText) findViewById(R.id.meal_description_edit_text);
        mealGIEditText = (EditText) findViewById(R.id.meal_gi_edit_text);
        mealStartDate = (EditText) findViewById(R.id.meal_start_date_edit_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_home:
            displayMainActivity();
            break;
        case R.id.action_target_module:
            displayMealMenuListActivity();
            break;
        case R.id.action_settings:
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void displayMealMenuListActivity() {
        Intent intent = new Intent(this, MealMenuListActivity.class);
        startActivity(intent);
    }

    public void saveMealPublic(){
        saveMeal();
        displayMealMenuListActivity();
    }
//    private void setSaveMealButtonListener(){
//        View.OnClickListener listenerSave = new View.OnClickListener() {
//            @Override public void onClick(View v) {
//               saveMeal();
//                displayMealMenuListActivity();
//            }
//        } ;
//       Button saveMealButton = (Button)findViewById(R.id.save_meal_button);
//        saveMealButton.setOnClickListener(listenerSave);
//    }
}
