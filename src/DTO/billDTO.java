package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class billDTO {
    private int maHD;
    private int maKH;
    private int tongtien;
    private LocalDate ngaymua;

    public int getmaHD() { return maHD; }
    public void setmaHD(int maHD) { this.maHD = maHD; }
    public int getmaKH() { return maKH; }
    public void setmaKH(int maKH) { this.maKH = maKH; }
    public int gettongtien() { return tongtien; }
    public void settongtien(int tongtien) { this.tongtien = tongtien; }
    public LocalDate getngaymua() { return ngaymua; }
    public void setngaymua(LocalDate ngaymua) { this.ngaymua = ngaymua; }
}
