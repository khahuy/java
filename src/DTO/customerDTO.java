package DTO;

import java.util.Date;

public class customerDTO {
    private int maKH;
    private String tenKH;
    private String SDT;
    private Date ngayDangKy;

    public int getmaKH() { return maKH; }
    public void setmaKH(int maKH) { this.maKH = maKH; }
    public String gettenKH() { return tenKH; }
    public void settenKH(String tenKH) { this.tenKH = tenKH; }
    public String getSDT() { return SDT; }
    public void setSDT(String SDT) { this.SDT = SDT;}
    public Date getSignIn() { return ngayDangKy; }
    public void setSignIn(Date ngayDangKy) { this.ngayDangKy = ngayDangKy;}
}
