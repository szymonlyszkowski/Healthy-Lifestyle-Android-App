package pl.com.healthylifestyle.healthylifestyle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.com.healthylifestyle.healthylifestyle.R;
import pl.com.healthylifestyle.healthylifestyle.model.Target;

/**
 * @author alisowsk
 */
public class TargetAdapter extends ArrayAdapter<Target> {
    private List<Target> targetList;
    private Context context;

    public TargetAdapter (Context context, int resource, List<Target> objects) {
        super(context, resource, objects);
        this.context = context;
        this.targetList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TargetItemHolder targetItemHolder;
        View view = convertView;
        if (view == null) {
            targetItemHolder = new TargetItemHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.activity_target_custom_row, parent, false);
            targetItemHolder.name = (TextView) view.findViewById(R.id.target_custom_row);
            view.setTag(targetItemHolder);
        } else {
            targetItemHolder = (TargetItemHolder) view.getTag();
        }

        Target eItem = this.targetList.get(position);
        targetItemHolder.name.setText(eItem.getName());

        return view;
    }

    private static class TargetItemHolder {
        private TextView name;
    }
}

