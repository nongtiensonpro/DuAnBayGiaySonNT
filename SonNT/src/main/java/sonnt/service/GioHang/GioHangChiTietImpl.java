package sonnt.service.GioHang;

import sonnt.model.GioHangChiTiet;

import java.util.UUID;

public interface GioHangChiTietImpl {

    void add(GioHangChiTiet gioHangChiTiet);

    GioHangChiTiet findById(UUID id);

    void update(UUID id, GioHangChiTiet update);

}
