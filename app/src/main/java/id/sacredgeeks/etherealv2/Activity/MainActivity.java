package id.sacredgeeks.etherealv2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import id.sacredgeeks.etherealv2.Adapter.TugasAdapter;
import id.sacredgeeks.etherealv2.DB.DataSourceTugas;
import id.sacredgeeks.etherealv2.Model.Jadwal;
import id.sacredgeeks.etherealv2.Model.Tugas;
import id.sacredgeeks.etherealv2.R;

public class MainActivity extends AppCompatActivity {

    private CaldroidFragment caldroidFragment;
    private String tanggal;
    private TextView datenow;
    TugasAdapter Tadapter;
    private ListView lsjadwal;
    private ListView lstugas;
    private Button movetoday;

    private Bundle state;

    private SimpleDateFormat formatter;

    public static final String MY_PREFS_NAME = "Settings";

    @Override  //Memunculkan tombol menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();

        if(id == R.id.action_settings) {
            AddSchedule AS = new AddSchedule();
            AS.message(MainActivity.this, "Kamu Klik Pengaturan");
            //startActivity(new Intent(MainActivity.this,Preferences.class));
        } else if(id == R.id.action_setweeks) {
            AddSchedule AS = new AddSchedule();
            AS.message(MainActivity.this, "Kamu Klik Atur Jadwal Mingguan");
        } else if (id == R.id.about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override //Mencegah ukuran tanggal berubah ketika rotasi
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }
    }

    public void cekSP() {
        //Cek apakah sudah ada SP?
        SharedPreferences get = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        if (get.contains("count") && get.contains("waktupengingat") && get.contains("suarapengingat")) {
            //Call snackbar dari java AddSchedule
            AddSchedule AS = new AddSchedule();
            AS.message(MainActivity.this,"SP Sudah ada");
        } else {
            //Shared Preferences (Untuk Menyimpan Data selama app itu diinstal) add / edit
            SharedPreferences.Editor create = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            create.putInt("count", 0);
            create.putString("waktupengingat", "Belum Diatur");
            create.putString("suarapengingat", "Belum Diatur");
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

    public void move(View view) {

    }

    public void about() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.layout_about,null);
        builder.setView(dialogview);

        builder.setTitle("About Ethereal V2");
        builder.setCancelable(false)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = savedInstanceState;

        tanggal = new SimpleDateFormat("dd MMM yyyy").format(new Date());
        SetData();

        cekSP();

        AddSchedule AS = new AddSchedule();
        AS.message(this,"Main Activity Loaded !");

        movetoday = (Button) findViewById(R.id.movetoday);
        formatter = new SimpleDateFormat("dd MMM yyyy");
        datenow = (TextView) findViewById(R.id.date_now);

        movetoday.setEnabled(false);

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

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.kalender, caldroidFragment);
        t.commit();

        String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
        datenow.setText("Tanggal : "+date);
        tanggal = date;

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                datenow = (TextView) findViewById(R.id.date_now);
                datenow.setText("Tanggal : "+formatter.format(date));
                tanggal = formatter.format(date);
                SetData();
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

    private DataSourceTugas tugasData;
    ArrayList <Tugas> tugas;

    public void SetData() {
        //adapter = new JadwalAdapter(this,mockupdata());
        tugasData = new DataSourceTugas(this);
        tugasData.open();
        tugas = tugasData.getTugas(tanggal);
        tugasData.close();

        Tadapter = new TugasAdapter(this,tugas);
        //lsjadwal = (ListView) findViewById(R.id.lsjadwal);
        lstugas = (ListView) findViewById(R.id.lstugas);

        //lsjadwal.setAdapter(adapter);
        lstugas.setAdapter(Tadapter);

        //setListViewHeightBasedOnItems(lsjadwal);
        setListViewHeightBasedOnItems(lstugas);

        lstugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //AddSchedule as = new AddSchedule();
                //as.message(MainActivity.this,"Mantab Jiwa");

                Tugas t = new Tugas();
                t = tugas.get(i);

                final CharSequence[] items = {"Perbaharui", "Hapus", "Lihat Rinci"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Aksi "+t.getDate_Created());

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        Tugas tt = new Tugas();
                        tt = tugas.get(i);

                        AddSchedule AS = new AddSchedule();
                        if(item == 0) {
                            AS.message(MainActivity.this, "Kamu klik perbaharui" );
                        } else if (item == 1) {
                            AS.message(MainActivity.this, "ID : "+tt.getID_Tugas() );
                        } else if (item == 2) {
                            Intent i = new Intent(MainActivity.this, DetailActivity.class);
                            i.putExtra("tgldibuat",tt.getDate_Created());
                            i.putExtra("namatugas",tt.getNamatugas());
                            i.putExtra("kettugas",tt.getKeterangan());
                            i.putExtra("deadlinetugas",tt.getDeadline());
                            i.putExtra("jamtugas",tt.getAlarm());
                            i.putExtra("pathimg",tt.getPathImg());
                            startActivity(i);

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
