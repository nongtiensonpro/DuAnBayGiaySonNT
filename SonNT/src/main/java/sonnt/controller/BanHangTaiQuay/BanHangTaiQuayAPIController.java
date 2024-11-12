package sonnt.controller.BanHangTaiQuay;

import sonnt.model.*;
import sonnt.service.GiayTheThao.GiayTheThaoChiTietService;
import sonnt.service.GiayTheThao.GiayTheThaoService;
import sonnt.service.HoaDon.HoaDonChiTietServie;
import sonnt.service.HoaDon.HoaDonService;
import sonnt.service.admin.MauSacService;
import sonnt.service.admin.SizeService;
import sonnt.service.ChuongTrinhGiamGiaChiTietGiayTheThaoService;
import sonnt.service.ChuongTrinhGiamGiaChiTietHoaDonService;
import sonnt.service.ChuongTrinhGiamGiaHoaDonService;
import sonnt.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class BanHangTaiQuayAPIController {
    private final HoaDonChiTietServie hoaDonChiTietservie;
    private final HoaDonService hdService;
    private final KhachHangService khService;
    private final GiayTheThaoChiTietService gttctService;
    private final MauSacService msService;
    private final SizeService sizeService;
    private final GiayTheThaoService gttService;
    private final ChuongTrinhGiamGiaHoaDonService ctggHDService;
    private final ChuongTrinhGiamGiaChiTietGiayTheThaoService ctggctService;
    private final ChuongTrinhGiamGiaChiTietHoaDonService ctggcthdService;

    public BanHangTaiQuayAPIController(HoaDonChiTietServie hoaDonChiTietservie, HoaDonService hdService, KhachHangService khService, GiayTheThaoChiTietService gttctService, MauSacService msService, SizeService sizeService, GiayTheThaoService gttService, ChuongTrinhGiamGiaHoaDonService ctggHDService, ChuongTrinhGiamGiaChiTietGiayTheThaoService ctggctService, ChuongTrinhGiamGiaChiTietHoaDonService ctggcthdService) {
        this.hoaDonChiTietservie = hoaDonChiTietservie;
        this.hdService = hdService;
        this.khService = khService;
        this.gttctService = gttctService;
        this.msService = msService;
        this.sizeService = sizeService;
        this.gttService = gttService;
        this.ctggHDService = ctggHDService;
        this.ctggctService = ctggctService;
        this.ctggcthdService = ctggcthdService;
    }

    @GetMapping("/gtt")
    private List<GiayTheThao> getAllGtt() {
        return gttService.getAll();
    }

    @GetMapping("/gttct/mauSac/{idGtt}")
    public List<MauSac> getAllMauSacByIdGtt(@PathVariable("idGtt") UUID idGtt) {
        System.out.println(msService.getAllByIdGtt(idGtt).size());
        return msService.getAllByIdGtt(idGtt);
    }

    @GetMapping("/gttct/size/{idGtt}")
    public List<Size> getAllSizeByIdGtt(@PathVariable("idGtt") UUID idGtt) {

        return sizeService.getAllByIdGtt(idGtt);
    }

    @PostMapping("/gttct/{idGtt}")
    public GiayTheThaoChiTiet getGttctByGttAndMauSacAndSize(@PathVariable("idGtt") UUID idGtt,
                                                            @RequestBody String[] mauSacAndSize) {
        UUID idMs = UUID.fromString(mauSacAndSize[0]);
        UUID idSize = UUID.fromString(mauSacAndSize[1]);

        GiayTheThaoChiTiet gttct = gttctService.getByGttMsSize(idGtt, idMs, idSize);
        if (gttct == null) {
            GiayTheThaoChiTiet newGttct = new GiayTheThaoChiTiet();
            newGttct.setSoLuong("0");
            return newGttct;
        }
        return gttct;
    }

    @GetMapping("/gtt/checkCount")
    public int checkCount() {
        return gttService.countGttInCtgg();
    }

    @GetMapping("/ctggct/{idGtt}")
    public ChuongTrinhGiamGiaChiTietGiayTheThao getCtggByIdGtt(@PathVariable("idGtt") UUID idGtt) {
        GiayTheThao gtt = gttService.getOne(idGtt);
        return ctggctService.getByGiayTheThao(gtt);
    }

    @GetMapping("/gttct")
    private List<GiayTheThaoChiTiet> getAllGttct() {
        return gttctService.getAll();
    }

    @PostMapping("/gttct/search")
    private List<GiayTheThao> searchByName(@RequestBody String[] name) {
        // System.out.println("Name: " + name);
        List<GiayTheThao> result = gttService.findGiayTheThao(name[0]);
        // System.out.println("GTTCT: "+result.get(0).getGiayTheThao().
        // getTenGiayTheThao());
        return result;
    }

    @PostMapping("/kh/search")
    private List<KhachHang> searchByTen(@RequestBody String[] name) {
        // System.out.println("Name: " + name);
        List<KhachHang> result = khService.findKhachHang(name[0]);
        // System.out.println(": "+result.get(0).getGiayTheThao().getTenGiayTheThao());
        return result;
    }

    @GetMapping("/kh")
    public List<KhachHang> getAllKH() {

        return khService.getAll();

    }

    @GetMapping("/hdct/{id}")
    public List<HoaDonChiTiet> getAllHDCTByid(@PathVariable("id") UUID id) {
        return hoaDonChiTietservie.getListByID(id);
    }

    @PutMapping("hdct/{id}")
    public HoaDonChiTiet putHDCTByid(@PathVariable("id") UUID id, @RequestBody int soLuong) {
        return hoaDonChiTietservie.updateHDCT(soLuong, id);
    }

    @DeleteMapping("/hdct/{id}")
    public void deleteHDCTByid(@PathVariable("id") UUID id) {
        hoaDonChiTietservie.removeHDCTByID(id);
    }

    @GetMapping("/hd/unpaid")
    public List<HoaDon> getAllHDC() {

        return hdService.hoaDonCho();

    }

    @DeleteMapping("/hd/{id}")
    public void removeHDCByID(@PathVariable("id") UUID id) {

        hdService.removeHDCByID(id);

    }

    @PostMapping("/hdct")
    public HoaDonChiTiet addHDCT(@RequestBody HoaDonChiTiet hdct) {

        return hoaDonChiTietservie.addHDCT(hdct);
    }

    @PostMapping("/hdct/check/{id}")
    public boolean check(@PathVariable("id") UUID id, @RequestBody int soLuong) {
        return hoaDonChiTietservie.checkSoLuongGTTCT(id, soLuong);
    }

    @PostMapping("/hd")
    public HoaDon createHoaDon(@RequestBody String[] strings, HttpSession session) {
        HoaDon hd = new HoaDon();

        // if (strings[0].length() != 0) {
        // UUID idUser = UUID.fromString(strings[0]);
        // User user = new User();
        // user.setId(idUser);
        // hd.setUser(user);
        // }

        UUID idUser = (UUID) session.getAttribute("idUser");
        User user = new User();
        user.setId(idUser);
        hd.setUser(user);

        if (strings[1].length() != 0) {
            UUID idKH = UUID.fromString(strings[1]);
            KhachHang kh = new KhachHang();
            kh.setId(idKH);
            hd.setKhachHang(kh);
        }

        return hdService.createHD(hd);
    }

    @PostMapping("/hd/pay/{id}")
    public String pay(@PathVariable("id") UUID id, @RequestBody String[] tt) {
        return hdService.thanhToan(id, tt);
    }

    @GetMapping("/hd/{id}")
    public HoaDon Hd(@PathVariable("id") UUID id) {
        return hdService.findId(id);
    }

    @PostMapping("/ctgg")
    public List<ChuongTrinhGiamGiaHoaDon> getAllChuongTrinhGiamGiaHoaDon(@RequestBody String[] list) {
        String sl = list[0];
        BigDecimal tt = new BigDecimal(list[0]);
        return ctggHDService.getAllBySlTT(sl, tt);
    }

    @GetMapping("/ctgg/{id}")
    public ChuongTrinhGiamGiaHoaDon getCtggHDByID(@PathVariable("id") UUID id) {
        return ctggHDService.getOne(id);
    }

    @PostMapping("/ctgg/create/{idHD}")
    public ChuongTrinhGiamGiaChiTietHoaDon createCtgghd(@PathVariable("idHD") UUID idHD, @RequestBody String[] array) {
        UUID idCtgghd = UUID.fromString(array[0]);
        BigDecimal tt = new BigDecimal(array[1]);
        return ctggcthdService.createCTGGChiTietHoaDon(idCtgghd, idHD, tt);
    }
}
