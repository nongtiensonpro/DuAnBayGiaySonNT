package sonnt.controller.BanHangTaiQuay;

import sonnt.model.HoaDonChiTiet;
import sonnt.service.ChuongTrinhGiamGiaHoaDonService;
import sonnt.service.GiayTheThao.GiayTheThaoChiTietService;
import sonnt.service.HoaDon.HoaDonChiTietServie;
import sonnt.service.HoaDon.HoaDonService;
import sonnt.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("BanHangTaiQuay")
public class BanHangTaiQuayController {

    private final GiayTheThaoChiTietService gttctService;
    private final HoaDonService hoaDonService;
    private final HoaDonChiTietServie hoaDonChiTietservie;
    private final KhachHangService khachHangService;
    private final ChuongTrinhGiamGiaHoaDonService ctggHDService;

    public BanHangTaiQuayController(GiayTheThaoChiTietService gttctService, HoaDonService hoaDonService, HoaDonChiTietServie hoaDonChiTietservie, KhachHangService khachHangService, ChuongTrinhGiamGiaHoaDonService ctggHDService) {
        this.gttctService = gttctService;
        this.hoaDonService = hoaDonService;
        this.hoaDonChiTietservie = hoaDonChiTietservie;
        this.khachHangService = khachHangService;
        this.ctggHDService = ctggHDService;
    }


    @GetMapping()
    public String getView(Model model) {


        model.addAttribute("list", gttctService.getAll());
        model.addAttribute("listHDC", hoaDonService.hoaDonCho());
        model.addAttribute("listKH", khachHangService.getAll());

        return "BanHangTaiQuay/BanHangTaiQuayTest";

    }

    @GetMapping("thanhToan/{id}")
    public String getViewPay(Model model, @PathVariable("id") UUID id,HttpSession session) {

        UUID idUser = (UUID) session.getAttribute("idUser");

        // Kiểm tra idUser có tồn tại
        if (idUser != null) {
            // Thực hiện lưu idUser vào hóa đơn
            hoaDonService.luuIdUserVaoHoaDon(id, idUser);

            List<HoaDonChiTiet> listHDCT = hoaDonChiTietservie.getListByID(id);
            int sl = 0;
            BigDecimal tt = new BigDecimal(0);
            for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
                sl+=Integer.parseInt(hoaDonChiTiet.getSoLuong());
                BigDecimal soLuong = new BigDecimal(hoaDonChiTiet.getSoLuong());
                tt = tt.add(hoaDonChiTiet.getDonGia().multiply(soLuong));
            }
            model.addAttribute("list", listHDCT);
            model.addAttribute("tt", tt);
            model.addAttribute("listCtgg", ctggHDService.getAllBySlTT(String.valueOf(sl), tt));
            model.addAttribute("id", id);

            return "BanHangTaiQuay/thanhToan";


        }else {

//           Nhân viên hoặc admin chưa đăng nhập tài khoản
            return "redirect:/UserLog/login";

        }

    }


}


