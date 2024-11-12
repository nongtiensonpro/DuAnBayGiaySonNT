package sonnt.service.admin;

import sonnt.model.TrongLuong;
import sonnt.repository.SanPham.AllGiayTheThao.TrongLuongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrongLuongService {

    private final TrongLuongRepository trongLuongRepository;

    public TrongLuongService(TrongLuongRepository trongLuongRepository) {
        this.trongLuongRepository = trongLuongRepository;
    }

    public List<TrongLuong> getAll() {
        return trongLuongRepository.findAll();
    }

    public TrongLuong getOne(UUID id) {
        return trongLuongRepository.findById(id).orElse(null);
    }

    public void save(TrongLuong trongLuong) {
        this.trongLuongRepository.save(trongLuong);
    }

    public void delete(UUID id) {
        this.trongLuongRepository.deleteById(id);
    }

    public List<TrongLuong> searchByTen(String tenTrongLuong) {
        return trongLuongRepository.findByTenTrongLuongContainingOrderById(tenTrongLuong);
    }

}
