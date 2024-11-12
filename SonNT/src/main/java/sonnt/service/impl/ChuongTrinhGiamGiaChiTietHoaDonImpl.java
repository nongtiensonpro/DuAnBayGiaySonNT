package sonnt.service.impl;

import sonnt.model.ChuongTrinhGiamGiaChiTietHoaDon;
import sonnt.model.ChuongTrinhGiamGiaHoaDon;

import java.util.List;

public interface ChuongTrinhGiamGiaChiTietHoaDonImpl {

    public List<ChuongTrinhGiamGiaChiTietHoaDon> getById(ChuongTrinhGiamGiaHoaDon ctggHD);
    public List<ChuongTrinhGiamGiaChiTietHoaDon> getAllByTrangThaiHD();

}
