package school.jabjai.jabjaiapp.Come2School;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.antonyt.infiniteviewpager.InfiniteViewPager;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.roomorama.caldroid.CalendarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import hirondelle.date4j.DateTime;
import school.jabjai.jabjaiapp.R;

public class Come2SchoolCalendarFragment extends Fragment {
    private final String TAG = "CalendarFragment";

    private final String RESTORE_KEY = "CALDROID_SAVED_STATE";

    private CaldroidFragment caldroidFragment;

    //Important variables.
    private ArrayList<Date> eventGreenDate = new ArrayList<>();
    private ArrayList<Date> eventRedDate = new ArrayList<>();
    private ArrayList<DateTime> selectedDate = new ArrayList<>();
    private Date select1Date = null;
    private Date select2Date = null;

    public Come2SchoolCalendarFragment() {
        // Required empty public constructor
    }


    public static Come2SchoolCalendarFragment newInstance() {
        Come2SchoolCalendarFragment fragment = new Come2SchoolCalendarFragment();
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
        View view = inflater.inflate(R.layout.fragment_come2_school_calendar, container, false);
        initCalendar(savedInstanceState);

        return view;
    }

    private void initCalendar(Bundle savedInstanceState) {
        //Set up calendar
        caldroidFragment = new CaldroidFragment();

        //If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, RESTORE_KEY);
        } else { // If activity is created from fresh
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            caldroidFragment.setArguments(args);
        }

        //Attach to the activity
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar, caldroidFragment);
        t.commit();

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        //Setup listener
        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                //disable day in month
                disableDay(caldroidFragment.getMonth(), caldroidFragment.getYear());
                manageSelectDateCalendar(date);
            }

            @Override
            public void onChangeMonth(int month, int year) {
                //disable day in month
                disableDay(month, year);
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    //disable day in month
                    disableDay(caldroidFragment.getMonth(), caldroidFragment.getYear());
                }
            }
        };

        caldroidFragment.setCaldroidListener(listener);
    }

    //Calendar management function
    private void disableDay(int month, int year) {

        caldroidFragment.clearDisableDates();

        InfiniteViewPager dateViewPager = caldroidFragment.getDateViewPager();
        ArrayList<Date> disableDates = new ArrayList<>();

        if (dateViewPager != null) {
            ArrayList<DateTime> datesInMonth = dateViewPager.getDatesInMonth();

            if (datesInMonth == null)
                return;

            for (DateTime dt : datesInMonth) {
                int d_month = dt.getMonth();
                int d_year = dt.getYear();

                if (d_month != month || d_year != year) {
                    disableDates.add(CalendarHelper.convertDateTimeToDate(dt));
                }
            }

            if (disableDates.size() != 0) {
                caldroidFragment.setDisableDates(disableDates);
            } else {
                Log.d(TAG, "DisableDates ArrayList Size = 0 !!!");
            }
        } else {
            Log.d(TAG, "dateViewPager is null !!!");
        }
    }

    private void manageSelectDateCalendar(Date date) {
        Map<String, Object> caldroidData = caldroidFragment.getCaldroidData();
        ArrayList<DateTime> disableDate = (ArrayList<DateTime>) caldroidData.get(CaldroidFragment.DISABLE_DATES);

        //If date are in disable time then return
        DateTime c_dt = CalendarHelper.convertDateToDateTime(date);
        if (disableDate.contains(c_dt)) {
            return;
        }

        //Define selected color
        ColorDrawable selectDrawableColor = new ColorDrawable(getResources().getColor(R.color.orange));
        ColorDrawable red = new ColorDrawable(Color.RED);
        ColorDrawable green = new ColorDrawable(Color.GREEN);

        if (select1Date != null && select2Date != null) { //When select range is actived
            for (DateTime dt : selectedDate) {
                caldroidFragment.clearBackgroundDrawableForDateTime(dt);
            }

            caldroidFragment.clearSelectedDates();

            for (Date redDate : eventRedDate) {
                caldroidFragment.setBackgroundDrawableForDate(red, redDate);
            }

            for (Date greenDate : eventGreenDate) {
                caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
            }

            select1Date = null;
            select2Date = null;
        }

        if (select1Date == null) { //If select first time set select date bg to orange
            select1Date = date;
            caldroidFragment.setBackgroundDrawableForDate(selectDrawableColor, select1Date);
        } else if (select2Date == null) {

            select2Date = date;
            if (select1Date.after(select2Date)) {
                Date bufferDate = select1Date;
                select1Date = select2Date;
                select2Date = bufferDate;
            }

            caldroidFragment.setSelectedDates(select1Date, select2Date); //If select second time select date in range select1Date-select2Date

            //set background color for selectdate in range
            ArrayList<DateTime> selectDate = (ArrayList<DateTime>) caldroidData.get(CaldroidFragment.SELECTED_DATES);
            for (int i = 0; i < selectDate.size(); i++) {
                selectedDate.add(selectDate.get(i));

                if (!disableDate.contains(selectDate.get(i))) {
                    caldroidFragment.setBackgroundDrawableForDateTime(selectDrawableColor, selectDate.get(i));
                }
            }
        }

        caldroidFragment.refreshView();
    }
}
