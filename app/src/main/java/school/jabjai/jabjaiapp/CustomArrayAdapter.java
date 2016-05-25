package school.jabjai.jabjaiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 25/5/2559.
 */
public class CustomArrayAdapter extends BaseAdapter {

    ArrayList<String> dataList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public CustomArrayAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        dataList = data;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.custom_layout_1, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(dataList.get(position));

        return view;
    }
}
