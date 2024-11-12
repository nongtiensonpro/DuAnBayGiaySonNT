package sonnt.service.admin;

import sonnt.model.DayGiay;
import sonnt.repository.SanPham.AllGiayTheThao.DayGiayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

;

@Service
public class DayGiayService {

    private final DayGiayRepository dayGiayRepository;

    public DayGiayService(DayGiayRepository dayGiayRepository) {
        this.dayGiayRepository = dayGiayRepository;
    }

    public List<DayGiay> getAll() {
        return dayGiayRepository.findAll();
    }

    public DayGiay getOne(UUID id) {
        return dayGiayRepository.findById(id).orElse(null);
    }

    public void save(DayGiay dayGiay) {
        this.dayGiayRepository.save(dayGiay);
    }

    public void delete(UUID id) {
        this.dayGiayRepository.deleteById(id);
    }

    public List<DayGiay> searchByTen(String tenDayGiay) {
        return dayGiayRepository.findByTenDayGiayContainingOrderById(tenDayGiay);
    }
}
