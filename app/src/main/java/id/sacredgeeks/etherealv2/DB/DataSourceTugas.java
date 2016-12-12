package id.sacredgeeks.etherealv2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import id.sacredgeeks.etherealv2.Model.Tugas;

/**
 * Created by SacredGeeks on 12/12/2016.
 */

public class DataSourceTugas {
    private SQLiteDatabase database;
    private OpenHelper helper;

    private String[] allkolomtugas = {OpenHelper.T_KOLOM_ID_TUGAS,OpenHelper.T_KOLOM_NAMA,OpenHelper.T_KOLOM_KETERANGAN
            ,OpenHelper.T_KOLOM_DEADLINE,OpenHelper.T_KOLOM_ALARM,OpenHelper.T_KOLOM_PATHIMG};

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
        values.put(OpenHelper.T_KOLOM_NAMA, Tugas.getNamatugas());
        values.put(OpenHelper.T_KOLOM_KETERANGAN, Tugas.getKeterangan());
        values.put(OpenHelper.T_KOLOM_DEADLINE, Tugas.getDeadline());
        values.put(OpenHelper.T_KOLOM_ALARM, Tugas.getAlarm());
        values.put(OpenHelper.T_KOLOM_PATHIMG, Tugas.getPathImg());

        return database.insert("tugas",null,values);
    }

    public List<Tugas> getAllTugas() {
        Cursor cursor = database.query("tugas",allkolomtugas,null,null,null,null,null);

        List<Tugas> tugass = new ArrayList<>();

        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Tugas tugas = new Tugas();
                tugas.setNamatugas(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_NAMA)));
                tugas.setKeterangan(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_KETERANGAN)));
                tugas.setDeadline(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_DEADLINE)));
                tugas.setAlarm(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_ALARM)));
                tugas.setPathImg(cursor.getString(cursor.getColumnIndex(OpenHelper.T_KOLOM_PATHIMG)));
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
