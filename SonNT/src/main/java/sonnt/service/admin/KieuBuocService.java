package sonnt.service.admin;

import sonnt.model.KieuBuoc;
import sonnt.repository.SanPham.AllGiayTheThao.KieuBuocRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KieuBuocService {

    private final KieuBuocRepository kieuBuocRepository;

    public KieuBuocService(KieuBuocRepository kieuBuocRepository) {
        this.kieuBuocRepository = kieuBuocRepository;
    }

    public List<KieuBuoc> getAll() {
        return kieuBuocRepository.findAll();
    }

    public KieuBuoc getOne(UUID id) {
        return kieuBuocRepository.findById(id).orElse(null);
    }

    public void save(KieuBuoc kieuBuoc) {
        this.kieuBuocRepository.save(kieuBuoc);
    }

    public void delete(UUID id) {
        this.kieuBuocRepository.deleteById(id);
    }

    public List<KieuBuoc> searchByTen(String tenKieuBuoc) {
        return kieuBuocRepository.findByTenKieuBuocContainingOrderById(tenKieuBuoc);
    }

}
