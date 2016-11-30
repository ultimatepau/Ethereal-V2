package id.sacredgeeks.etherealv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AddSchedule extends AppCompatActivity {

    private TextView tvTanggal;
    private String tanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        tvTanggal = (TextView) findViewById(R.id.tvtanggal);

        Intent i = getIntent();
        tanggal = i.getStringExtra("tanggal");

        tvTanggal.setText("Tanggal : "+tanggal);

    }

}
