package sonnt.service.HoaDon;

import sonnt.model.HoaDon;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface HoaDonServiceImpl {

    Page<HoaDon> listHoaDonFindByKhachHangAndTrangThai(UUID idKhachHang,int trangThai,Integer pageNo,Integer size);

    Page<HoaDon> listHoaDonFindByTrangThai(Integer pageNo, Integer size, int trangThai);

    HoaDon findId(UUID id);

    void update(UUID id, HoaDon updateHoaDon);

    String tongSl(UUID id);

    HoaDon finByMa(String ma);

}
