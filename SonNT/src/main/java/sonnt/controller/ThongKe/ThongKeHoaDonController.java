package sonnt.controller.ThongKe;

import sonnt.model.HoaDon;
import sonnt.repository.HoaDon.HoaDonChiTietRepository;
import sonnt.repository.HoaDon.HoaDonRepository;
import sonnt.repository.SanPham.GiayTheThao.GiayTheThaoChiTietRepository;
import sonnt.repository.SanPham.GiayTheThao.GiayTheThaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ThongKeHoaDonController {

    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final GiayTheThaoRepository giayTheThaoRepository;
    private final GiayTheThaoChiTietRepository giayTheThaoChiTietRepository;
    private final HoaDonRepository hoaDonRepository;

    public ThongKeHoaDonController(HoaDonChiTietRepository hoaDonChiTietRepository, GiayTheThaoRepository giayTheThaoRepository, GiayTheThaoChiTietRepository giayTheThaoChiTietRepository, HoaDonRepository hoaDonRepository) {
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.giayTheThaoRepository = giayTheThaoRepository;
        this.giayTheThaoChiTietRepository = giayTheThaoChiTietRepository;
        this.hoaDonRepository = hoaDonRepository;
    }

    @GetMapping("/thongKeChuanData")
    public String thongKeChuanData(Model model){

        //Tổng danh thu cho sản phẩm đã bán
        BigDecimal tongDoanhThu = hoaDonRepository.tinhTongDoanhThu();
        model.addAttribute("tongDoanhThu",tongDoanhThu);

        //Tổng đơn hàng cho sản phẩm đã bán
        Integer tongDonHang = hoaDonRepository.tongDonHang();
        model.addAttribute("tongDonHang",tongDonHang);

        //Tổng số lượng sản phẩm sẵn có trong kho
        Integer totalQuantity = giayTheThaoChiTietRepository.sumSoLuong();
        model.addAttribute("totalQuantity",totalQuantity);

        //
        Integer tongHoaDonTaiQuay = hoaDonRepository.tongHoaDonTaiQuay();
        model.addAttribute("tongHoaDonTaiQuay",tongHoaDonTaiQuay);

        //Tổng số lượng hóa đơn online đã bán
        Integer tongHoaDonOnline = hoaDonRepository.tongHoaDonOnline();
        model.addAttribute("tongHoaDonOnline",tongHoaDonOnline);



        return "ThongKe/test";

    }



   //Thống kê hóa đơn

    @GetMapping("/thongke-data-hoadon")
    @ResponseBody
    public String thongKeDataHD() {
        List<HoaDon> hdct = hoaDonRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        // Thêm module để hỗ trợ Java 8 date/time
        objectMapper.registerModule(new JavaTimeModule());

        try {
            String jsonData = objectMapper.writeValueAsString(hdct);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



}
