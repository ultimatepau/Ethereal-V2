package id.sacredgeeks.etherealv2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSchedule extends AppCompatActivity {

    private TextView tvTanggal;
    private String tanggal;
    private int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    private EditText inputTugas;
    private EditText inputDeadline;
    private EditText inputketTugas;
    private Calendar myCalendar;
    /* private int PICK_IMAGE_REQUEST = 1; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        inputDeadline = (EditText) findViewById(R.id.inputdeadline);
        inputTugas = (EditText) findViewById(R.id.inputnamatugas);
        inputketTugas = (EditText) findViewById(R.id.inputkettugas);
        inputDeadline.setInputType(0);

        tvTanggal = (TextView) findViewById(R.id.tvtanggal);

        Intent i = getIntent();
        tanggal = i.getStringExtra("tanggal");

        tvTanggal.setText("Tanggal : "+tanggal);

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

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
        inputDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddSchedule.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void browseImg(View view) {
        /*
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        */
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/imgTugas/";
        File newdir = new File(dir);
        newdir.mkdirs();

        count++;
        String file = dir+count+".jpg";
        File newfile = new File(file);
        String nomedia = dir+".nomedia";
        File createnomedia = new File(nomedia);
        try {
            newfile.createNewFile();
            createnomedia.createNewFile();
        }
        catch (IOException e)
        {
        }

        Uri outputFileUri = Uri.fromFile(newfile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Foto Tersimpan !", Toast.LENGTH_SHORT).show();
            Log.d("CameraDemo", "Pic saved");
        }
    }


    private void updateLabel() {
        myCalendar = Calendar.getInstance();

        String myFormat = "dd MMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        inputDeadline.setText(sdf.format(myCalendar.getTime()));
    }
}
