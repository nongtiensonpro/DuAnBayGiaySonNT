package sonnt.service.ViTien.Impl;

import sonnt.model.ViTien;

import java.util.UUID;

public interface viTienServiceImpl {

    void add(ViTien viTien);

    void update(UUID id, ViTien updateViTien);

    ViTien findByIdKhachHang(UUID id);

}
