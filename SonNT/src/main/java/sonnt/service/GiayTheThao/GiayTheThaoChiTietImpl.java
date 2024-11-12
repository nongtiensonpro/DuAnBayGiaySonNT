package sonnt.service.GiayTheThao;

import sonnt.model.GiayTheThaoChiTiet;

import java.util.UUID;

public interface GiayTheThaoChiTietImpl {

    //id là của giầy thể thao giầy thể thao chi tiết sẽ tìm tất cả dữ liệu để trả về
    GiayTheThaoChiTiet findIdByIdGiayTheThao(UUID id, UUID idMauSac , UUID idSize);

}
