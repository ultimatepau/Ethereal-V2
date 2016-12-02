package id.sacredgeeks.etherealv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private String tanggal;
    private TextView datenow;
    private ListView lsjadwal;
    private ListView lstugas;
    private int angka;

    JadwalAdapter adapter;

    public static final String MY_PREFS_NAME = "Settings";


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
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }

        if (dialogCaldroidFragment != null) {
            dialogCaldroidFragment.saveStatesToKey(outState,
                    "DIALOG_CALDROID_SAVED_STATE");
        }
    }

    public void cekSP() {
        //Cek apakah sudah ada SP?
        SharedPreferences get = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        if (get.contains("count")) {
            //Call snackbar dari java AddSchedule
            AddSchedule AS = new AddSchedule();
            AS.message(MainActivity.this,"SP Sudah ada");
        } else {
            //Shared Preferences (Untuk Menyimpan Data selama app itu diinstal) add / edit
            SharedPreferences.Editor create = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            create.putInt("count", 0);
            create.commit();

            AddSchedule AS = new AddSchedule();
            AS.message(MainActivity.this,"SP Dibuat");
        }
    }

    private ArrayList<Jadwal> mockupdata() {
        ArrayList<Jadwal> item = new ArrayList<>();
        item.add(new Jadwal("Ujian Bahasa Inggris","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Sunda","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Indonesia","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));
        item.add(new Jadwal("Ujian Bahasa Jepang","Jam 10","10.30","AdaFoto"));

        return item;
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int itemPos = 0;
            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }

    private ListView ls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cekSP();

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

        adapter = new JadwalAdapter(this,mockupdata());

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                datenow = (TextView) findViewById(R.id.date_now);
                datenow.setText("Tanggal : "+formatter.format(date));
                tanggal = formatter.format(date);

                if (tanggal.equals("18 Dec 2016")) {
                    SetData();
                }
                else {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    ls = (ListView) findViewById(R.id.lsjadwal);
                    setListViewHeightBasedOnItems(ls);
                }
            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {

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

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                datenow = (TextView) findViewById(R.id.date_now);
                datenow.setText("Tanggal : "+formatter.format(date));
                tanggal = formatter.format(date);
            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {

            }

        };

        caldroidFragment.setCaldroidListener(listener);
    }

    public void SetData() {
        adapter = new JadwalAdapter(this,mockupdata());

        ls = (ListView) findViewById(R.id.lsjadwal);

        ls.setAdapter(adapter);

        setListViewHeightBasedOnItems(ls);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //AddSchedule as = new AddSchedule();
                //as.message(MainActivity.this,"Mantab Jiwa");

                Jadwal jj = new Jadwal();
                jj = mockupdata().get(i);

                final CharSequence[] items = {"Perbaharui", "Hapus", "Lihat Rinci"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Aksi "+jj.getNamajadwal());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        AddSchedule AS = new AddSchedule();
                        if(item == 0) {
                            AS.message(MainActivity.this, "Kamu klik perbaharui" );
                        } else if (item == 1) {
                            AS.message(MainActivity.this, "Kamu klik Hapus" );
                        } else if (item == 2) {
                            AS.message(MainActivity.this, "Kamu klik Lihat Rinci" );
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void addSchedule(View view) {
        Intent i = new Intent(getApplicationContext(),AddSchedule.class);
        i.putExtra("tanggal",tanggal);
        startActivity(i);
    }
}
