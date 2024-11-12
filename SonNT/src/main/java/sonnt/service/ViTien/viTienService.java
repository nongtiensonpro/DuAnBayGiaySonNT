package sonnt.service.ViTien;

import sonnt.model.ViTien;
import org.springframework.stereotype.Service;
import sonnt.service.ViTien.Impl.viTienServiceImpl;
import sonnt.repository.ViTien.viTienRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class viTienService implements viTienServiceImpl{

    public viTienService(sonnt.repository.ViTien.viTienRepository viTienRepository) {
        this.viTienRepository = viTienRepository;
    }

    viTienRepository viTienRepository;

    @Override
    public void add(ViTien viShop) {

        viTienRepository.save(viShop);

    }

    @Override
    public void update(UUID id, ViTien updateVi) {

        Optional<ViTien> optionalViShop = viTienRepository.findById(id);

        if (optionalViShop.isPresent()){

            ViTien viShop = optionalViShop.get();
            viShop.setMaViTien(updateVi.getMaViTien());
            viShop.setKhachHang(updateVi.getKhachHang());
            viShop.setThanhTien(updateVi.getThanhTien());
            viShop.setTrangThai(updateVi.getTrangThai());

            viTienRepository.save(viShop);

        }
    }

    @Override
    public ViTien findByIdKhachHang(UUID id) {

        return viTienRepository.findByIdKhachHang(id);

    }




}
