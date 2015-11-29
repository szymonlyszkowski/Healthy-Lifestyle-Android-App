package pl.com.healthylifestyle.healthylifestyle;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import pl.com.healthylifestyle.healthylifestyle.adapter.TargetAdapter;
import pl.com.healthylifestyle.healthylifestyle.model.Target;

/**
 * @author alisowsk
 */
public class TargetActivity extends ListActivity {

    private List<Target> targets;
    private TargetAdapter targetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.healty_green));

        prepareTestData();

        targetsAdapter = new TargetAdapter(this, android.R.layout.simple_list_item_1, targets);
        setListAdapter(targetsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_target, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                displayMainActivity();
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //TODO open "modify window"
        System.out.println("To be implemented - modify target " + targets.get(position).getName());
    }

    public void addTargetEvent(View v) {
        Target newTarget = new Target("New", 100, 0);
        newTarget.save();
        targets.add(newTarget);
        targetsAdapter.notifyDataSetChanged();
    }

    private void prepareTestData(){
        targets = new Select().from(Target.class).execute();
        //new Delete().from(Target.class).execute();
        if(targets.size() == 0){
            targets = new ArrayList<>();
            targets.add(new Target("Loose weight", 80, 90));
            targets.add(new Target("Run km", 150, 0));
            targets.add(new Target("Eat carrot", 30, 0));
            targets.add(new Target("Go to jym", 10, 0));
        }
    }
}
