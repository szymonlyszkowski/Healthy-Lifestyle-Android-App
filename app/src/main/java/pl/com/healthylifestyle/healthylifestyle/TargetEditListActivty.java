package pl.com.healthylifestyle.healthylifestyle;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.activeandroid.query.Select;
import pl.com.healthylifestyle.healthylifestyle.adapter.TargetAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Target;

import java.util.List;

public class TargetEditListActivty extends ListActivity {

    private List<Target> targets;
    private TargetAdapter targetsAdapter;

    private void initFields() {
        this.targets = new Select().from(Target.class).execute();


        this.targetsAdapter = new TargetAdapter(this, android.R.layout.simple_list_item_1, targets);
        setListAdapter(targetsAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_edit_list);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.healty_green));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_target_edit_list_activty, menu);

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






}
