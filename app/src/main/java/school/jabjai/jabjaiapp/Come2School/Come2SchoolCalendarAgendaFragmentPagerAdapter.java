package school.jabjai.jabjaiapp.Come2School;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 26/5/2559.
 */
public class Come2SchoolCalendarAgendaFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = new String[]{"Calendar", "Agenda"};

    public Come2SchoolCalendarAgendaFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Come2SchoolCalendarFragment.newInstance();
            case 1:
                return Come2SchoolAgendaFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
