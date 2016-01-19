package pl.com.healthylifestyle.healthylifestyle;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import pl.com.healthylifestyle.healthylifestyle.adapter.MealAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Meal;
import pl.com.healthylifestyle.healthylifestyle.model.Target;
import pl.com.healthylifestyle.healthylifestyle.util.NotificationUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MealMenuListActivity extends ListActivity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd/HH-mm");

    private List<Meal> mealsList = new ArrayList<>();
    private MealAdapter mealsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu_layout);
        getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        mealsList = new Select().from(Meal.class).execute();
        prepareTestData();
        initFields();
        createMealRemoval();
        createMealAddition();

    }

    private void initFields() {
//        prepareTestData(); //TODO remove
        this.mealsListAdapter = new MealAdapter(this, android.R.layout.activity_list_item, mealsList);
        setListAdapter(mealsListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        } else if(id == R.id.action_set_notification){
            setNotification();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNotification() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MealMenuListActivity.this);
        dialog.setTitle("Set notification");

        final SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
        final int itemCount = getListView().getCount();
        final List<Integer> mealsIndexes = new ArrayList<>();
        final EditText input = new EditText(this);

        for(int i=itemCount-1; i >= 0; i--){
            if(checkedItemPositions.get(i)){
                mealsIndexes.add(i);
            }
        }

        input.setText("");
        input.setInputType(InputType.TYPE_CLASS_DATETIME);
        input.setHint("yyyy-MM-dd/HH-mm");
        dialog.setView(input);

        dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dateStr = input.getText().toString();
                try {
                    Date d = DATE_FORMAT.parse(dateStr);
                    for (int i = 0; i < mealsIndexes.size(); i++) {
                        Meal meal = mealsList.get(mealsIndexes.get(i));
                        meal.setReminder(d);
                        Notification newNotification = getNotification(meal);
                        int notificationId = UUID.randomUUID().hashCode();
                        meal.setNotificationId(notificationId);
                        scheduleNotification(meal.getReminder(), newNotification, notificationId);
                        meal.save();
                    }
                    recreate();
                    dialog.dismiss();
                } catch (ParseException e) {
                    setNotification();
                }
            }
        });
        dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
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

    private void scheduleNotification(Date notificationDate, Notification notification, int notificationId) {
        Intent notificationIntent = new Intent(this, NotificationUtil.class);
        notificationIntent.putExtra(NotificationUtil.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(NotificationUtil.NOTIFICATION, notification);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.setAction("Healthy Lifestyle " + notificationId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long difference = notificationDate.getTime() - System.currentTimeMillis();
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + difference, pendingIntent);
    }

    private Notification getNotification(Meal meal) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Healthy Lifestyle");
        builder.setContentText("Remember about " + meal.getName());
        builder.setSmallIcon(R.drawable.alarm_clock_icon);
        builder.setWhen(meal.getReminder().getTime());
        Notification notification = builder.build();
        return notification;
    }

}
