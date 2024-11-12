package sonnt.service.admin;

import sonnt.model.GiayTheThao;
import sonnt.model.MauSac;
import sonnt.repository.SanPham.AllGiayTheThao.MauSacRepository;
import sonnt.service.GiayTheThao.GiayTheThaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacService {

    private final MauSacRepository mauSacRepository;
    private final GiayTheThaoService gttService;

    public MauSacService(MauSacRepository mauSacRepository, GiayTheThaoService gttService) {
        this.mauSacRepository = mauSacRepository;
        this.gttService = gttService;
    }


    public List<MauSac> findMauSac(String tenMauSac) {
        if (tenMauSac != null) {
            return mauSacRepository.findByTenMauSac(tenMauSac);
        } else {
            return mauSacRepository.findAll();
        }
    }
    public List<MauSac> getAllByIdGtt(UUID idGtt) {
        GiayTheThao gtt = gttService.getOne(idGtt);
        return mauSacRepository.findByGtt(gtt);
    }

}
