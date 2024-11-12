package sonnt.service.admin;

import sonnt.model.CongDung;
import sonnt.repository.SanPham.AllGiayTheThao.CongDungRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

;

@Service
public class CongDungService {

    private final CongDungRepository congDungRepository;

    public CongDungService(CongDungRepository congDungRepository) {
        this.congDungRepository = congDungRepository;
    }

    public List<CongDung> getAll() {
        return congDungRepository.findAll();
    }

    public CongDung getOne(UUID id) {
        return congDungRepository.findById(id).orElse(null);
    }

    public void save(CongDung congDung) {
        this.congDungRepository.save(congDung);
    }

    public void delete(UUID id) {
        this.congDungRepository.deleteById(id);
    }

    public List<CongDung> searchByTen(String tenCongDung) {
        return congDungRepository.findByTenCongDungContainingOrderById(tenCongDung);
    }
}
