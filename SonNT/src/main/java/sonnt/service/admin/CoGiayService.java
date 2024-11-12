package sonnt.service.admin;

import sonnt.model.CoGiay;
import sonnt.repository.SanPham.AllGiayTheThao.CoGiayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoGiayService {

    private final CoGiayRepository coGiayRepository;

    public CoGiayService(CoGiayRepository coGiayRepository) {
        this.coGiayRepository = coGiayRepository;
    }

    public List<CoGiay> findCoGiay(String tenCoGiay) {
        if (tenCoGiay != null) {
            return coGiayRepository.findByTenCoGiayContaining(tenCoGiay);
        } else {
            return coGiayRepository.findAll();
        }
    }
}
