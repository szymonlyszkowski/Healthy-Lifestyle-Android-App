package pl.com.healthylifestyle.healthylifestyle;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import pl.com.healthylifestyle.healthylifestyle.model.Target;

/**
 * @author alisowsk
 */
public class TargetAddActivity extends ActionBarActivity {
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText currentValueEditText;
    private EditText desiredValueEditText;
    private Target myTarget;
    private boolean newTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_add_new);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getSerializable("target") != null) {
            myTarget = (Target) bundle.getSerializable("target");
        }
        if (myTarget == null) {
            myTarget = new Target();
            newTarget = true;
        } else {
            newTarget = false;
        }

        initFields();
    }

    private void initFields() {
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        currentValueEditText = (EditText) findViewById(R.id.currentValueEditText);
        desiredValueEditText = (EditText) findViewById(R.id.desiredValueEditText);

        if (!newTarget) {
            nameEditText.setText(myTarget.getName());
            descriptionEditText.setText(myTarget.getDescription());
            currentValueEditText.setText(String.valueOf(myTarget.getCurrentValue()));
            desiredValueEditText.setText(String.valueOf(myTarget.getDesiredValue()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_target_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                displayMainActivity();
                break;
            case R.id.action_target_module:
                displayTargetListActivity();
                break;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTargetEvent(View v) {
        if(validate()){
            saveTarget();
            displayTargetListActivity();
        }
    }

    private ArrayList getCheckedIntemsOfTargetList(ListView list){
        int itemsAmount = list.getAdapter().getCount();
        ArrayList<Integer> checkedPostion = new ArrayList();
        for (int position=0; position<itemsAmount; ++position){
            if(list.isItemChecked(position)){
               checkedPostion.add(position);
            }
        }
        return checkedPostion;
    }


    private boolean validate() {
        //TODO extract class, maybe find some nice way to validate - library with annotations?
        if(null == nameEditText.getText() || nameEditText.getText().toString().isEmpty()){
            nameEditText.setError("Name field cannot be empty");
            return false;
        }
        if(null == currentValueEditText.getText() || currentValueEditText.getText().toString().isEmpty()){
            currentValueEditText.setError("Current value cannot be empty");
            return false;
        }
        if(!currentValueEditText.getText().toString().matches("^[1-9]\\d*(\\.\\d+)?$")){
            currentValueEditText.setError("Current value has to be number");
            return false;
        }
        if(null == desiredValueEditText.getText() || desiredValueEditText.getText().toString().isEmpty()){
            desiredValueEditText.setError("Desired value cannot be empty");
            return false;
        }
        if(!desiredValueEditText.getText().toString().matches("^[1-9]\\d*(\\.\\d+)?$")){
            desiredValueEditText.setError("Desired value has to be number");
            return false;
        }
        return true;
    }

    private void saveTarget() {
        String name = nameEditText.getText().toString();
        String description = "";
        if(null != descriptionEditText.getText() && descriptionEditText.getText().toString().isEmpty()){
            description = descriptionEditText.getText().toString();
        }
        double currentValue = Double.valueOf(currentValueEditText.getText().toString());
        double desiredValue = Double.valueOf(desiredValueEditText.getText().toString());

        myTarget.setCurrentValue(currentValue);
        myTarget.setDesiredValue(desiredValue);
        myTarget.setDescription(description);
        myTarget.setName(name);

        if (desiredValue < currentValue) {
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Target finished!")
                    .setContentText("Target " + myTarget.getName() + " has been finished!")
                    .setSmallIcon(R.drawable.app_notification_icon)
                    .build();

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            int notificationId = 1;
            mNotificationManager.notify(notificationId, notification);
        }

        myTarget.save();
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TargetAddActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void displayMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void displayTargetListActivity() {
        Intent intent = new Intent(this, TargetListActivity.class);
        startActivity(intent);
    }
}
