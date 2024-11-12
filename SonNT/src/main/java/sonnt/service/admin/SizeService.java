package sonnt.service.admin;


import sonnt.model.GiayTheThao;
import sonnt.model.Size;
import sonnt.repository.SanPham.AllGiayTheThao.SizeRepository;
import sonnt.service.GiayTheThao.GiayTheThaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;
    private final GiayTheThaoService gttService;

    public SizeService(SizeRepository sizeRepository, GiayTheThaoService gttService) {
        this.sizeRepository = sizeRepository;
        this.gttService = gttService;
    }


    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    public Size getOne(UUID id) {
        return sizeRepository.findById(id).orElse(null);
    }

    public void save(Size size) {
        this.sizeRepository.save(size);
    }

    public void delete(UUID id) {
        this.sizeRepository.deleteById(id);
    }

    public List<Size> getAllByIdGtt(UUID idGtt) {
        GiayTheThao gtt = gttService.getOne(idGtt);
        return sizeRepository.getSizeByGtt(gtt);
    }

}
