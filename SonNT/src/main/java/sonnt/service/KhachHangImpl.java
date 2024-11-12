package sonnt.service;

import sonnt.model.KhachHang;

import java.util.UUID;

public interface KhachHangImpl {

    KhachHang findById(UUID id);

    KhachHang findByEmail(String email);

    void update(UUID id, KhachHang update);

}
