package id.sacredgeeks.etherealv2.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SacredGeeks on 11/28/2016.
 */

public class OpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_eteherealv2";

    public static final String T_KOLOM_ID_TUGAS = "ID_TUGAS";
    public static final String T_KOLOM_NAMA = "NAMA";
    public static final String T_KOLOM_KETERANGAN = "KETERANGAN";
    public static final String T_KOLOM_DEADLINE = "DEADLINE";
    public static final String T_KOLOM_ALARM = "ALARM";
    public static final String T_KOLOM_PATHIMG = "PATHIMG";
    public static final String T_KOLOM_CREATED = "DATE_CREATED";

    public static final String J_KOLOM_ID_JADWAL = "ID_JADWAL";
    public static final String J_KOLOM_NAMA = "NAMA";
    public static final String J_KOLOM_KETERANGAN = "KETERANGAN";
    public static final String J_KOLOM_DAY = "DAY";
    public static final String J_KOLOM_ALARM = "ALARM";
    public static final String J_KOLOM_PATHIMG = "PATHIMG";



    public static final String TABLE_CREATE = "CREATE TABLE jadwal ("+J_KOLOM_ID_JADWAL+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                                                +J_KOLOM_NAMA+" TEXT, "+J_KOLOM_KETERANGAN+" TEXT, "
                                                +J_KOLOM_DAY+"DATE,"+J_KOLOM_ALARM+"TIME,"+J_KOLOM_PATHIMG+" TEXT)";

    public static final String TABLE_CREATE2 = "CREATE TABLE tugas ("+T_KOLOM_ID_TUGAS+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                                                +T_KOLOM_NAMA+" TEXT, "+T_KOLOM_KETERANGAN+" TEXT,"+T_KOLOM_DEADLINE+" DATE,"
                                                +T_KOLOM_ALARM+" TIME,"+T_KOLOM_PATHIMG+" TEXT,"+T_KOLOM_CREATED+" DATE)";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        sqLiteDatabase.execSQL(TABLE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST tugas");
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST jadwal");
    }
}
