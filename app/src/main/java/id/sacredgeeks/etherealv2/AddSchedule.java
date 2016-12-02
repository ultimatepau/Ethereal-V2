package id.sacredgeeks.etherealv2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddSchedule extends AppCompatActivity {

    private int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    private static final int REQUEST_IMAGE = 100;
    private int angka;

    private String tanggal;
    String imagePath;
    public static final String MY_PREFS_NAME = "Settings";


    File destination;

    private EditText inputTugas;
    private EditText inputDeadline;
    private EditText inputketTugas;
    private TextView tvTanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        inputDeadline = (EditText) findViewById(R.id.inputdeadline);
        inputTugas = (EditText) findViewById(R.id.inputnamatugas);
        inputketTugas = (EditText) findViewById(R.id.inputkettugas);

        tvTanggal = (TextView) findViewById(R.id.tvtanggal);

        Intent i = getIntent();
        tanggal = i.getStringExtra("tanggal");

        tvTanggal.setText("Tanggal : "+tanggal);

    }

    public void browseImg(View view) {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+ "imgTugas");
        if (!folder.exists()) {
            folder.mkdirs();
        } else {

        }

        //Ambil Data
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        angka = prefs.getInt("count",0); //0 is the default value.

        //Debug tambahin angka
        SharedPreferences.Editor edit = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        int temp = angka + 1;
        edit.putInt("count", temp);
        edit.commit();

        String name = "Foto"+angka+".jpg";
        destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/imgTugas/"+name);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK ){
            try {
                FileInputStream in = new FileInputStream(destination);
                //BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inSampleSize = 10;
                imagePath = destination.getAbsolutePath();

                //Bitmap bmp = BitmapFactory.decodeStream(in, null, options);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        else{
            //Ambil Data
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            angka = prefs.getInt("count",0); //0 is the default value.

            //Debug kurangin angka
            SharedPreferences.Editor edit = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            int temp = angka - 1;
            edit.putInt("count", temp);
            edit.commit();

            Toast.makeText(getApplicationContext(),"Request Cancelled",Toast.LENGTH_SHORT).show();
        }
    }

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public void inputDate(View view) {
        new DatePickerDialog(AddSchedule.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "dd MMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        inputDeadline.setText(sdf.format(myCalendar.getTime()));
    }

    public void submit(View view) {
            message(AddSchedule.this,"Nama : "+inputTugas.getText());

        new CountDownTimer(1500, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                message(AddSchedule.this,"Keterangan : "+inputketTugas.getText());
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();

        new CountDownTimer(2500, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                message(AddSchedule.this,"Deadline : "+inputDeadline.getText());
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();

        new CountDownTimer(4500, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                message(AddSchedule.this,"ImagePath : "+imagePath);
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }

    public void message(Activity activity,String message) {
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }
}
