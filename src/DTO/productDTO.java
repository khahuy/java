package DTO;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class productDTO {
    private int maSP;
    private String tenSP;
    private String loaiSP;
    private String theloai;
    private int giaca;
    private LocalDate ngayxuatban;

    public int getmaSP(){ return maSP;}
    public void setmaSP(int maSP){ this.maSP = maSP;}
    public String gettenSP(){ return tenSP;}
    public void settenSP(String tenSP){ this.tenSP = tenSP;}
    public String getloaiSP(){ return loaiSP;}
    public void setloaiSP(String loaiSP){ this.loaiSP = loaiSP;}
    public String gettheloai(){ return theloai;}
    public void settheloai(String theloai){ this.theloai = theloai;}
    public int getgiaca(){ return giaca;}
    public void setgiaca(int giaca){ this.giaca = giaca;}
    public LocalDate getngayxuatban(){ return ngayxuatban;}
    public void setngayxuatban(LocalDate ngayxuatban){ this.ngayxuatban = ngayxuatban;}

}
