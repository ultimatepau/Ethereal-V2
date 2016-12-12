package id.sacredgeeks.etherealv2.Model;

/**
 * Created by SacredGeeks on 12/2/2016.
 */

public class Tugas {

    public Tugas(String namatugas, String keterangan, String deadline, String alarm, String pathImg,String id_tugas) {
        Namatugas = namatugas;
        Keterangan = keterangan;
        Deadline = deadline;
        Alarm = alarm;
        PathImg = pathImg;
        ID_Tugas = id_tugas;
    }

    private String Namatugas;
    private String Keterangan;
    private String Deadline;
    private String Alarm;
    private String PathImg;

    public String getID_Tugas() {
        return ID_Tugas;
    }

    public void setID_Tugas(String ID_Tugas) {
        this.ID_Tugas = ID_Tugas;
    }

    private String ID_Tugas;

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

    public Tugas() {

    }

}
