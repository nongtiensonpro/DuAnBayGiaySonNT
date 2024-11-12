package sonnt.service.admin;

import sonnt.model.HuongDanBaoQuan;
import sonnt.repository.SanPham.AllGiayTheThao.HuongDanBaoQuanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuongDanBaoQuanService {


    private final HuongDanBaoQuanRepository huongDanBaoQuanRepository;

    public HuongDanBaoQuanService(HuongDanBaoQuanRepository huongDanBaoQuanRepository) {
        this.huongDanBaoQuanRepository = huongDanBaoQuanRepository;
    }

    public List<HuongDanBaoQuan> findHuongDanBaoQuan(String tenHuongDanBaoQuan) {
        if (tenHuongDanBaoQuan != null) {
            return huongDanBaoQuanRepository.findByTenHuongDanBaoQuanContaining(tenHuongDanBaoQuan);
        } else {
            return huongDanBaoQuanRepository.findAll();
        }
    }

}
