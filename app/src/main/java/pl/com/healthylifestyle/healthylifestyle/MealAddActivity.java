package pl.com.healthylifestyle.healthylifestyle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MealAddActivity extends ActionBarActivity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd/HH-mm");

    private EditText mealNameEditText;
    private EditText mealCaloriesEditText;
    private EditText mealDescriptionEditText;
    private EditText mealGIEditText;
    private EditText mealStartDate;
    private EditText mealReminderDate;
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
        Date startDate = null;
        try {
            startDate = DATE_FORMAT.parse(mealStartDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double calories = Double.parseDouble(mealCaloriesEditText.getText().toString());
        if(null != mealDescriptionEditText.getText() && mealDescriptionEditText.getText().toString().isEmpty()){
            description = mealDescriptionEditText.getText().toString();
        }
        Date reminderDate = null;
        if(null != mealReminderDate.getText() && !mealReminderDate.getText().toString().isEmpty()) {
            try {
                reminderDate = DATE_FORMAT.parse(String.valueOf(mealReminderDate.getText().toString()));
            } catch (ParseException e) {
            }
        }

        myMeal.setName(name);
        myMeal.setDescription(description);
        myMeal.setGI(GI);
        myMeal.setDescription(description);
        myMeal.setCaloriesAmount(calories);
        myMeal.setStartMealTime(startDate);
        myMeal.setReminder(reminderDate);
        myMeal.save();
    }

    private void init_meal_addition_fields() {
        mealNameEditText = (EditText) findViewById(R.id.meal_name_edit_text);
        mealCaloriesEditText = (EditText) findViewById(R.id.meal_calories_edit_text);
        mealDescriptionEditText = (EditText) findViewById(R.id.meal_description_edit_text);
        mealGIEditText = (EditText) findViewById(R.id.meal_gi_edit_text);
        mealStartDate = (EditText) findViewById(R.id.meal_start_date_edit_text);
        mealReminderDate = (EditText) findViewById(R.id.meal_reminder_date_edit_text);
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

    public void saveMealPublic(View v){
        if(validate()){
            saveMeal();
            displayMealMenuListActivity();
        }
    }

    private boolean validate() {
        //TODO extract class, maybe find some nice way to validate - library with annotations?
        if(null == mealNameEditText.getText() || mealNameEditText.getText().toString().isEmpty()){
            mealNameEditText.setError("Name field cannot be empty");
            return false;
        }
        if(null == mealNameEditText.getText() || mealNameEditText.getText().toString().isEmpty()){
            mealNameEditText.setError("Current value field cannot be empty");
            return false;
        }
        if(!mealCaloriesEditText.getText().toString().matches("^[1-9]\\d*(\\.\\d+)?$")){
            mealCaloriesEditText.setError("Current value has to be number");
            return false;
        }
        if(null == mealDescriptionEditText.getText() || mealDescriptionEditText.getText().toString().isEmpty()){
            mealDescriptionEditText.setError("Meal description field cannot be empty");
            return false;
        }
        if(null == mealGIEditText.getText() || mealGIEditText.getText().toString().isEmpty()){
            mealGIEditText.setError("GI field field cannot be empty");
            return false;
        }
        if(!mealGIEditText.getText().toString().matches("^[1-9]\\d*(\\.\\d+)?$")){
            mealGIEditText.setError("GI value has to be number");
            return false;
        }
        if(null == mealStartDate.getText() || mealStartDate.getText().toString().isEmpty()){
            mealStartDate.setError("Meal start date field cannot be empty");
            return false;
        }

        try {
            DATE_FORMAT.parse(String.valueOf(mealStartDate.getText()));
        } catch (ParseException e) {
            mealStartDate.setError("Meal start date has to be in format yyyy-MM-dd/HH-mm");
            return false;
        }

        if(null != mealReminderDate.getText() && !mealReminderDate.getText().toString().isEmpty()) {
            try {
                DATE_FORMAT.parse(String.valueOf(mealReminderDate.getText()));
            } catch (ParseException e) {
                mealReminderDate.setError("Meal start date has to be in format yyyy-MM-dd/HH-mm");
                return false;
            }
        }

        return true;
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
