package school.jabjai.jabjaiapp.Come2School;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 26/5/2559.
 */
public class ComeToSchoolAgendaAdapter extends ArrayAdapter<ComeToSchoolAgendaItem> {
    ArrayList<ComeToSchoolAgendaItem> agendaList = new ArrayList<>();

    public ComeToSchoolAgendaAdapter(Context context, int resource, ArrayList<ComeToSchoolAgendaItem> objects) {
        super(context, resource, objects);
        agendaList = objects;
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(school.jabjai.jabjaiapp.R.layout.come2school_agenda_list_view, null);

        TextView tvDate = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvDate);
        TextView tvComeTime = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvComeTime);
        TextView tvComeStatus = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvComeStatus);
        TextView tvLeaveTime = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvLeaveTime);
        TextView tvLeaveStatus = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvLeaveStatus);
        TextView tvRemark = (TextView) v.findViewById(school.jabjai.jabjaiapp.R.id.tvRemark);

        tvDate.setText("วันที่ " + agendaList.get(position).date);
        tvComeTime.setText("เวลาเข้า : " + agendaList.get(position).comeTime + " น.");
        tvComeStatus.setText("สถานะ : " + agendaList.get(position).comeStatus);
        tvLeaveTime.setText("เวลาออก : " + agendaList.get(position).leaveTime + " น.");
        tvLeaveStatus.setText("สถานะ : " + agendaList.get(position).leaveStatus);
        tvRemark.setText("หมายเหตุ : " + agendaList.get(position).remark);

        return v;
    }
}
