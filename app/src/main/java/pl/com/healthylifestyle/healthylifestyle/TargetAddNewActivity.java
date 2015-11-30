package pl.com.healthylifestyle.healthylifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import pl.com.healthylifestyle.healthylifestyle.model.Target;

/**
 * @author alisowsk
 */
public class TargetAddNewActivity extends ActionBarActivity {
    private EditText nameEditText;
    private EditText currentValueEditText;
    private EditText desiredValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_add_new);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.healty_green));

        initFields();
    }

    private void initFields() {
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        currentValueEditText = (EditText) findViewById(R.id.currentValueEditText);
        desiredValueEditText = (EditText) findViewById(R.id.desiredValueEditText);
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
        //TODO validate
        saveTarget();
        displayTargetListActivity();
    }

    private void saveTarget() {
        String name = nameEditText.getText().toString();
        double currentValue = Double.valueOf(currentValueEditText.getText().toString());
        double desiredValue = Double.valueOf(desiredValueEditText.getText().toString());
        Target newTarget = new Target(name, currentValue, desiredValue);
        newTarget.save();
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
