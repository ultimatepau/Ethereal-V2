package id.sacredgeeks.etherealv2.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.sacredgeeks.etherealv2.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tgldibuat,namatugas,kettugas,deadlinetugas,jamtugas;
    private ImageView fototugas;
    public String f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tgldibuat = (TextView) findViewById(R.id.tgldibuatTugas);
        namatugas= (TextView) findViewById(R.id.namaTugas);
        kettugas = (TextView) findViewById(R.id.keteranganTugas);
        deadlinetugas = (TextView) findViewById(R.id.deadlineTugas);
        jamtugas = (TextView) findViewById(R.id.jamTugas);

        fototugas = (ImageView) findViewById(R.id.ivTugas);

        Intent i = getIntent();
        String a = i.getStringExtra("tgldibuat");
        String b = i.getStringExtra("namatugas");
        String c = i.getStringExtra("kettugas");
        String d = i.getStringExtra("deadlinetugas");
        String e = i.getStringExtra("jamtugas");
        f = i.getStringExtra("pathimg");

        tgldibuat.setText("Tanggal Dibuat : "+a);
        namatugas.setText(b);
        kettugas.setText(c);
        deadlinetugas.setText(d);
        jamtugas.setText(e);

        fototugas.setImageBitmap(BitmapFactory.decodeFile(f));
        Toast.makeText(this, f, Toast.LENGTH_SHORT).show();

        fototugas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + f), "image/*");
                startActivity(intent);
            }
        });
    }
}
