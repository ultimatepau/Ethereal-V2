package id.sacredgeeks.etherealv2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import id.sacredgeeks.etherealv2.Model.Tugas;

/**
 * Created by SacredGeeks on 12/12/2016.
 */

public class DataSourceTugas {
    private SQLiteDatabase database;
    private OpenHelper helper;

    private String[] allkolomtugas = {OpenHelper.T_KOLOM_ID_TUGAS,OpenHelper.T_KOLOM_NAMA,OpenHelper.T_KOLOM_KETERANGAN
            ,OpenHelper.T_KOLOM_DEADLINE,OpenHelper.T_KOLOM_ALARM,OpenHelper.T_KOLOM_PATHIMG,OpenHelper.T_KOLOM_CREATED};

    public DataSourceTugas(Context context) {
        helper = new OpenHelper(context);
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public long createTugas(Tugas Tugas) {
        ContentValues values = new ContentValues();
        values.put(OpenHelper.T_KOLOM_CREATED, Tugas.getDate_Created());
        values.put(OpenHelper.T_KOLOM_NAMA, Tugas.getNamatugas());
        values.put(OpenHelper.T_KOLOM_KETERANGAN, Tugas.getKeterangan());
        values.put(OpenHelper.T_KOLOM_DEADLINE, Tugas.getDeadline());
        values.put(OpenHelper.T_KOLOM_ALARM, Tugas.getAlarm());
        values.put(OpenHelper.T_KOLOM_PATHIMG, Tugas.getPathImg());

        return database.insert("tugas",null,values);
    }

    public ArrayList<Tugas> getTugas(String date) {
        database = helper.getReadableDatabase();
        Cursor cursor = database.query("tugas",allkolomtugas,OpenHelper.T_KOLOM_DEADLINE+ "=?",new String[] {String.valueOf(date)},null,null,null,null);

        if (cursor != null)
            Log.v("LOL : ", String.valueOf(cursor.getCount()));

        ArrayList<Tugas> tugass = new ArrayList<>();

        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Tugas tugas = new Tugas();
                tugas.setID_Tugas(cursor.getInt(cursor.getColumnIndex(OpenHelper.T_KOLOM_ID_TUGAS)));
                tugas.setNamatugas(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_NAMA)));
                tugas.setKeterangan(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_KETERANGAN)));
                tugas.setDeadline(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_DEADLINE)));
                tugas.setAlarm(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_ALARM)));
                tugas.setPathImg(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_PATHIMG)));
                tugas.setDate_Created(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_CREATED)));
                tugass.add(tugas);
            }
        }
        return tugass;
    }

    public int updateTugas(Tugas Tugas) {
        ContentValues values = new ContentValues();
        values.put(OpenHelper.T_KOLOM_NAMA, Tugas.getNamatugas());
        values.put(OpenHelper.T_KOLOM_KETERANGAN, Tugas.getKeterangan());
        values.put(OpenHelper.T_KOLOM_DEADLINE, Tugas.getDeadline());
        values.put(OpenHelper.T_KOLOM_ALARM, Tugas.getAlarm());
        values.put(OpenHelper.T_KOLOM_PATHIMG, Tugas.getPathImg());

        return database.update("tugas",values,OpenHelper.T_KOLOM_ID_TUGAS + "=?",new String[] {String.valueOf(Tugas.getID_Tugas())});
    }

    public void deleteTugas (Tugas Tugas) {
        database.delete("tugas",OpenHelper.T_KOLOM_ID_TUGAS + "=" + Tugas.getID_Tugas(),null);
    }

}
