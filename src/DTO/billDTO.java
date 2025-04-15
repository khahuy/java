package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class billDTO {
    private int maKH;
    private int maSP;
    private int soluong;
    private LocalDate ngaymua;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public int getmaKH() { return maKH; }
    public void setmaKH(int maKH) { this.maKH = maKH; }
    public int getmaSP() { return maSP; }
    public void setmaSP(int maSP) { this.maSP = maSP; }
    public int getsoluong() { return soluong; }
    public void setsoluong(int soluong) { this.soluong = soluong; }
    public LocalDate getngaymua() { return ngaymua; }
    public void setngaymua(LocalDate ngaymua) { this.ngaymua = ngaymua; }
}
