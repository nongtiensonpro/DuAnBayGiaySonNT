package sonnt.service.admin;

import sonnt.model.DinhTanGiay;
import sonnt.repository.SanPham.AllGiayTheThao.DinhTanGiayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DinhTanGiayService {

    private final DinhTanGiayRepository dinhTanGiayRepository;

    public DinhTanGiayService(DinhTanGiayRepository dinhTanGiayRepository) {
        this.dinhTanGiayRepository = dinhTanGiayRepository;
    }

    public List<DinhTanGiay> getAll() {
        return dinhTanGiayRepository.findAll();
    }

    public DinhTanGiay getOne(UUID id) {
        return dinhTanGiayRepository.findById(id).orElse(null);
    }

    public void save(DinhTanGiay dinhTanGiay) {
        this.dinhTanGiayRepository.save(dinhTanGiay);
    }

    public void delete(UUID id) {
        this.dinhTanGiayRepository.deleteById(id);
    }

    public List<DinhTanGiay> searchByTen(String tenDinhTanGiay) {

        return dinhTanGiayRepository.findByTenDinhTanGiayContainingOrderById(tenDinhTanGiay);

    }

}
