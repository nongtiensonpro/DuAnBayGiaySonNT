package sonnt.service.GioHang;

import sonnt.model.GioHang;

import java.util.UUID;

public interface GioHangImpl {

    GioHang findByIdKH(UUID id);
}
