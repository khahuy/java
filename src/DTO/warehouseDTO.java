package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class warehouseDTO {
    private int maSP;
    private String tenSP;
    private LocalDate ngaynhapkho;
    private int soluong;

    public int getmaSP(){ return maSP;}
    public void setmaSP(int maSP){ this.maSP = maSP;}
    public String gettenSP(){ return tenSP;}
    public void settenSP(String tenSP){ this.tenSP = tenSP;}
    public LocalDate getngayxuatban(){ return ngaynhapkho;}
    public void setngayxuatban(LocalDate ngaynhapkho){ this.ngaynhapkho = ngaynhapkho;}
    public int getsoluong(){ return soluong;}
    public void setsoluong(int soluong){ this.soluong = soluong;}
    
}
