package id.sacredgeeks.etherealv2.Model;

/**
 * Created by SacredGeeks on 12/2/2016.
 */

public class Tugas {

    public Tugas(String namatugas, String keterangan, String deadline, String alarm, String pathImg, int ID_Tugas, String date_Created) {
        Namatugas = namatugas;
        Keterangan = keterangan;
        Deadline = deadline;
        Alarm = alarm;
        PathImg = pathImg;
        this.ID_Tugas = ID_Tugas;
        Date_Created = date_Created;
    }

    private String Namatugas;
    private String Keterangan;
    private String Deadline;
    private String Alarm;
    private String PathImg;
    private int ID_Tugas;
    private String Date_Created;

    public String getNamatugas() {
        return Namatugas;
    }

    public void setNamatugas(String namatugas) {
        Namatugas = namatugas;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getAlarm() {
        return Alarm;
    }

    public void setAlarm(String alarm) {
        Alarm = alarm;
    }

    public String getPathImg() {
        return PathImg;
    }

    public void setPathImg(String pathImg) {
        PathImg = pathImg;
    }

    public int getID_Tugas() {
        return ID_Tugas;
    }

    public void setID_Tugas(int ID_Tugas) {
        this.ID_Tugas = ID_Tugas;
    }

    public String getDate_Created() {
        return Date_Created;
    }

    public void setDate_Created(String date_Created) {
        Date_Created = date_Created;
    }

    public Tugas() {

    }

}
