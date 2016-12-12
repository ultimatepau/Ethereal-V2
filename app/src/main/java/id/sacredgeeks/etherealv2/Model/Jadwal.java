package id.sacredgeeks.etherealv2.Model;

/**
 * Created by SacredGeeks on 12/2/2016.
 */

public class Jadwal {
    private String Namajadwal;
    private String Keterangan;
    private String Alarm;
    private String PathImg;

    public Jadwal() {

    }

    public Jadwal(String namajadwal, String keterangan, String alarm, String pathImg) {
        Namajadwal = namajadwal;
        Keterangan = keterangan;
        Alarm = alarm;
        PathImg = pathImg;
    }

    public String getNamajadwal() {
        return Namajadwal;
    }

    public void setNamajadwal(String namajadwal) {
        Namajadwal = namajadwal;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
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

}
