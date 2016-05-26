package school.jabjai.jabjaiapp.Come2School;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import school.jabjai.jabjaiapp.R;

public class Come2SchoolAgendaFragment extends Fragment {

    ListView come2schoolAgendaListView;

    public Come2SchoolAgendaFragment() {
        // Required empty public constructor
    }

    public static Come2SchoolAgendaFragment newInstance() {
        Come2SchoolAgendaFragment fragment = new Come2SchoolAgendaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_come2_school_agenda, container, false);
        init(view);

        return view;
    }

    void init(View view) {

        //Prepare data
        ArrayList<ComeToSchoolAgendaItem> come2schoolItem = new ArrayList<>();
        come2schoolItem.add(new ComeToSchoolAgendaItem("9/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));
        come2schoolItem.add(new ComeToSchoolAgendaItem("10/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));
        come2schoolItem.add(new ComeToSchoolAgendaItem("11/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));
        come2schoolItem.add(new ComeToSchoolAgendaItem("12/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));
        come2schoolItem.add(new ComeToSchoolAgendaItem("13/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));
        come2schoolItem.add(new ComeToSchoolAgendaItem("14/05/2559", "8.00", "ตรงเวลา", "16.00", "ตรงเวลา", "-"));

        come2schoolAgendaListView = (ListView) view.findViewById(R.id.come2schoolAgendaListView);
        ComeToSchoolAgendaAdapter adapter = new ComeToSchoolAgendaAdapter(getContext(), R.layout.come2school_agenda_list_view, come2schoolItem);
        come2schoolAgendaListView.setAdapter(adapter);

    }
}
