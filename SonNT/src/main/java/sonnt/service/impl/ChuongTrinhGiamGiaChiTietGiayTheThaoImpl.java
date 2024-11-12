package sonnt.service.impl;

import sonnt.model.ChuongTrinhGiamGiaChiTietGiayTheThao;
import sonnt.model.ChuongTrinhGiamGiaGiayTheThao;
import sonnt.model.GiayTheThao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChuongTrinhGiamGiaChiTietGiayTheThaoImpl {

    public void add(ChuongTrinhGiamGiaChiTietGiayTheThao gg);
    public void update(ChuongTrinhGiamGiaChiTietGiayTheThao gg, UUID id);
    public void deleteByCTGGandGTT(UUID gg, UUID gtt);
    public List<ChuongTrinhGiamGiaChiTietGiayTheThao> getAllByCTGG(ChuongTrinhGiamGiaGiayTheThao gg);
    public List<ChuongTrinhGiamGiaChiTietGiayTheThao> getList();
    public Page<ChuongTrinhGiamGiaChiTietGiayTheThao> pagination(Pageable pageable);
    public ChuongTrinhGiamGiaChiTietGiayTheThao getByCTGGAndGTT(ChuongTrinhGiamGiaGiayTheThao gg, GiayTheThao gtt);
    public void deleteById(UUID id);

}
