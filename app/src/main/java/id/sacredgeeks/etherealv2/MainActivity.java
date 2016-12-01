package id.sacredgeeks.etherealv2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private String tanggal;
    private TextView datenow;
    private ListView lsjadwal;
    private ListView lstugas;


    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();

        // Min date is last 7 days
        /* cal.add(Calendar.DATE, -7);
        Date blueDate = cal.getTime(); */

        // Max date is next 7 days
        /* cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date greenDate = cal.getTime(); */

        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
            ColorDrawable green = new ColorDrawable(Color.GREEN);
            // Jika ingin menambahkan warna
            //caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);
            //caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
            //caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            //caldroidFragment.setTextColorForDate(R.color.white, greenDate);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        datenow = (TextView) findViewById(R.id.date_now);

        caldroidFragment = new CaldroidFragment();

        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }  else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);

            caldroidFragment.setArguments(args);
        }

        //setCustomResourceForDates();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.kalender, caldroidFragment);
        t.commit();

        String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
        datenow.setText("Tanggal : "+date);
        tanggal = date;



        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                //    Toast.makeText(getApplicationContext(), formatter.format(date),
                //            Toast.LENGTH_SHORT).show();
                datenow = (TextView) findViewById(R.id.date_now);
                datenow.setText("Tanggal : "+formatter.format(date));
                tanggal = formatter.format(date);

            }

            @Override
            public void onChangeMonth(int month, int year) {
                /*
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show(); */
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                /*if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

        };

        caldroidFragment.setCaldroidListener(listener);
    }

    public void move(View view) {
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        caldroidFragment.refreshView();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR,false);
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.kalender, caldroidFragment);
        t.commit();

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                //    Toast.makeText(getApplicationContext(), formatter.format(date),
                //            Toast.LENGTH_SHORT).show();
                datenow = (TextView) findViewById(R.id.date_now);
                datenow.setText("Tanggal : "+formatter.format(date));
            }

            @Override
            public void onChangeMonth(int month, int year) {
                /*
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show(); */
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                /*if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

        };

        caldroidFragment.setCaldroidListener(listener);
    }

    public void addSchedule(View view) {
        Intent i = new Intent(getApplicationContext(),AddSchedule.class);
        i.putExtra("tanggal",tanggal);
        startActivity(i);
    }
}
