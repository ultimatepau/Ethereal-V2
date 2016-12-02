package id.sacredgeeks.etherealv2;

/**
 * Created by SacredGeeks on 12/2/2016.
 */

public class Jadwal {
    private String Namajadwal;
    private String Keterangan;
    private String Deadline;
    private String PathImg;

    public Jadwal() {

    }

    public Jadwal(String namajadwal, String keterangan, String deadline, String pathImg) {
        Namajadwal = namajadwal;
        Keterangan = keterangan;
        Deadline = deadline;
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

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getPathImg() {
        return PathImg;
    }

    public void setPathImg(String pathImg) {
        PathImg = pathImg;
    }
}
