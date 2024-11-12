package sonnt.service.ViTien;

import sonnt.model.GiaoDichViChiTiet;
import sonnt.service.ViTien.Impl.giaoDichViChiTietServiceImpl;
import sonnt.repository.ViTien.giaoDichViChiTietRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class giaoDichViChiTietService implements giaoDichViChiTietServiceImpl{

    public giaoDichViChiTietService(sonnt.repository.ViTien.giaoDichViChiTietRepository giaoDichViChiTietRepository) {
        this.giaoDichViChiTietRepository = giaoDichViChiTietRepository;
    }

    giaoDichViChiTietRepository giaoDichViChiTietRepository;

    @Override
    public List<GiaoDichViChiTiet> findAllByKhachHang(UUID id) {

        return giaoDichViChiTietRepository.findAllByKhachHang(id);

    }

    @Override
    public void add(GiaoDichViChiTiet giaoDichVi) {

        giaoDichViChiTietRepository.save(giaoDichVi);

    }

    @Override
    public void update(UUID id, GiaoDichViChiTiet updateGD) {
        Optional<GiaoDichViChiTiet> optionalGiaoDichVi = giaoDichViChiTietRepository.findById(id);
        if(optionalGiaoDichVi.isPresent()){
            GiaoDichViChiTiet giaoDichVi = optionalGiaoDichVi.get();

            giaoDichVi.setMaGiaoDichViChiTiet(updateGD.getMaGiaoDichViChiTiet());
            giaoDichVi.setDonGia(updateGD.getDonGia());
            giaoDichVi.setNgayGiaoDich(updateGD.getNgayGiaoDich());
            giaoDichVi.setUser(updateGD.getUser());
            giaoDichVi.setViTien(updateGD.getViTien());
            giaoDichVi.setHinhThuc(updateGD.getHinhThuc());
            giaoDichVi.setTrangThai(updateGD.getTrangThai());

            giaoDichViChiTietRepository.save(giaoDichVi);

        }
    }

    @Override
    public GiaoDichViChiTiet findByMa(String ma) {

        return giaoDichViChiTietRepository.findByMa(ma);

    }

}
