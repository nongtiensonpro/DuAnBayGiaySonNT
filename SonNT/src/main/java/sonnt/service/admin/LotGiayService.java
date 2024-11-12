package sonnt.service.admin;

import sonnt.repository.SanPham.AllGiayTheThao.LotGiayRepository;
import org.springframework.stereotype.Service;

@Service
public class LotGiayService {
    private final LotGiayRepository lotGiayRepository;

    public LotGiayService(LotGiayRepository lotGiayRepository) {
        this.lotGiayRepository = lotGiayRepository;
    }
}
