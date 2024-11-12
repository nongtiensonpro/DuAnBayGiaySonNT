package sonnt.service.BanHang;

import sonnt.model.GioHangChiTiet;
import sonnt.repository.BanHang.GioHangChiTietRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GioHangService {

    private final GioHangChiTietRepository gioHangChiTietRepository;

    public GioHangService(GioHangChiTietRepository gioHangChiTietRepository) {
        this.gioHangChiTietRepository = gioHangChiTietRepository;
    }

    //Todo code xóa giỏ hàng

    public void xoaSanPham(UUID gioHangChiTietId) {
        // Tìm thông tin giỏ hàng chi tiết cần xóa
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId).orElse(null);

        // Kiểm tra nếu giỏ hàng chi tiết tồn tại
        if (gioHangChiTiet != null) {
            // Xóa giỏ hàng chi tiết từ cơ sở dữ liệu
            gioHangChiTietRepository.delete(gioHangChiTiet);

            // Cập nhật thông tin giỏ hàng của người dùng nếu cần
            // Ví dụ: gioHangRepository.save(gioHang);

            // Các bước khác cần thiết để cập nhật thông tin giỏ hàng
        }
    }

}
