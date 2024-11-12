package sonnt.service;

import sonnt.model.ChuongTrinhGiamGiaChiTietHoaDon;
import sonnt.model.ChuongTrinhGiamGiaHoaDon;
import sonnt.model.HoaDon;
import sonnt.repository.ChuongTrinhGiamGia.ChuongTrinhGiamGiaChiTietHoaDonRepository;
import sonnt.service.HoaDon.HoaDonService;
import sonnt.service.impl.ChuongTrinhGiamGiaChiTietHoaDonImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ChuongTrinhGiamGiaChiTietHoaDonService implements ChuongTrinhGiamGiaChiTietHoaDonImpl {


    private final ChuongTrinhGiamGiaChiTietHoaDonRepository repo;

    public ChuongTrinhGiamGiaChiTietHoaDonService(ChuongTrinhGiamGiaChiTietHoaDonRepository repo, ChuongTrinhGiamGiaHoaDonService repGiamGiaHoaDonService, HoaDonService hDonService) {
        this.repo = repo;
        this.repGiamGiaHoaDonService = repGiamGiaHoaDonService;
        this.hDonService = hDonService;
    }

    @Override
    public List<ChuongTrinhGiamGiaChiTietHoaDon> getById(ChuongTrinhGiamGiaHoaDon ctggHD) {
        return repo.findAllByCtggHD(ctggHD);
    }

    private final ChuongTrinhGiamGiaHoaDonService repGiamGiaHoaDonService;

    @Override
    public List<ChuongTrinhGiamGiaChiTietHoaDon> getAllByTrangThaiHD() {
        return repo.getAllByTrangThaiHD();
    }

    private final HoaDonService hDonService;

    public ChuongTrinhGiamGiaChiTietHoaDon createCTGGChiTietHoaDon(UUID idCtggHD, UUID idHD, BigDecimal tongTien) {
        ChuongTrinhGiamGiaChiTietHoaDon ctggcthd = new ChuongTrinhGiamGiaChiTietHoaDon();
        ChuongTrinhGiamGiaHoaDon giamGiaHoaDon = repGiamGiaHoaDonService.getOne(idCtggHD);
        HoaDon hd = hDonService.findId(idHD);
        BigDecimal phanTramGiam = new BigDecimal(giamGiaHoaDon.getPhanTramGiam());

        BigDecimal soTienDaGiam = tongTien.multiply(phanTramGiam).divide(new BigDecimal(100));
        ctggcthd.setCtggHD(giamGiaHoaDon);
        ctggcthd.setHd(hd);
        ctggcthd.setSoTienDaGiam(soTienDaGiam);
        ctggcthd.setGhiChu("N/A");
        ctggcthd.setNgayTao(LocalDate.now().toString());
        ctggcthd.setNgaySua(LocalDate.now().toString());
        ctggcthd.setTrangThai(1);

        return repo.save(ctggcthd);
    }

}
