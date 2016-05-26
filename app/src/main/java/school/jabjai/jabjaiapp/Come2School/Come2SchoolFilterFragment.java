package school.jabjai.jabjaiapp.Come2School;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import school.jabjai.jabjaiapp.CustomArrayAdapter;
import school.jabjai.jabjaiapp.R;

public class Come2SchoolFilterFragment extends Fragment {
    public Come2SchoolFilterFragment() {
        // Required empty public constructor
    }

    public static Come2SchoolFilterFragment newInstance() {
        Come2SchoolFilterFragment fragment = new Come2SchoolFilterFragment();
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
        View view = inflater.inflate(R.layout.fragment_come2_school_filter, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        ArrayList<String> yearList = new ArrayList<String>();
        yearList.add("2557");
        yearList.add("2558");

        ArrayList<String> termList = new ArrayList<>();
        termList.add("1");
        termList.add("2");

        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("1");
        statusList.add("2");

        //Set up spinner year value
        Spinner spinnerYear = (Spinner) view.findViewById(R.id.spinnerYear);

        CustomArrayAdapter customAdapter1 = new CustomArrayAdapter(getContext(), yearList);
        spinnerYear.setAdapter(customAdapter1);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Set up spinner term value
        Spinner spinnerTerm = (Spinner) view.findViewById(R.id.spinnerTerm);

        CustomArrayAdapter customAdapter2 = new CustomArrayAdapter(getContext(), termList);
        spinnerTerm.setAdapter(customAdapter2);
        spinnerTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Set up spinner status value
        Spinner spinnerStatus = (Spinner) view.findViewById(R.id.spinnerStatus);

        CustomArrayAdapter customAdapter3 = new CustomArrayAdapter(getContext(), statusList);
        spinnerStatus.setAdapter(customAdapter3);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
