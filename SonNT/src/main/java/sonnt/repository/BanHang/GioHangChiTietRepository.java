package sonnt.repository.BanHang;

import sonnt.model.GiayTheThaoChiTiet;
import sonnt.model.GioHang;
import sonnt.model.GioHangChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    GioHangChiTiet findByGioHangAndGiayTheThaoChiTiet(GioHang gioHang, GiayTheThaoChiTiet giayTheThaoChiTiet);

    GioHangChiTiet findByGiayTheThaoChiTiet_Id(UUID idGiayTheThaoChiTiet);

    List<GioHangChiTiet> findByGioHangId(UUID idGioHang);

    Optional<GioHangChiTiet> findByGioHang_Id_AndGiayTheThaoChiTiet_Id(UUID gioHangId, UUID giayTheThaoChiTietId);


    @Modifying
    @Transactional
    @Query("DELETE FROM GioHangChiTiet ghct WHERE ghct.gioHang.id = :gioHangId AND ghct.giayTheThaoChiTiet.id = :giayTheThaoChiTietId")
    void deleteByGioHang_IdAndGiayTheThaoChiTiet_Id(UUID gioHangId, UUID giayTheThaoChiTietId);


}
