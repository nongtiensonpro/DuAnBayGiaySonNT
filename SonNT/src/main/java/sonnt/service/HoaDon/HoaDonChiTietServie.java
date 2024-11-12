package sonnt.service.HoaDon;

import sonnt.model.GiayTheThaoChiTiet;
import sonnt.model.HoaDon;
import sonnt.model.HoaDonChiTiet;
import sonnt.repository.HoaDon.HoaDonChiTietRepository;
import sonnt.repository.HoaDon.HoaDonRepository;
import sonnt.repository.SanPham.GiayTheThao.GiayTheThaoChiTietRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServie {

    private final HoaDonChiTietRepository repo;
    private final HoaDonRepository hdRepo;
    private final GiayTheThaoChiTietRepository giayTheThaoChiTietRepository;

    public HoaDonChiTietServie(HoaDonChiTietRepository repo, HoaDonRepository hdRepo, GiayTheThaoChiTietRepository giayTheThaoChiTietRepository) {
        this.repo = repo;
        this.hdRepo = hdRepo;
        this.giayTheThaoChiTietRepository = giayTheThaoChiTietRepository;
    }


    //Todo code bán hàng tại quầy
    public List<HoaDonChiTiet> getListByID(UUID id) {
        HoaDon hd = hdRepo.findById(id).get();
        return repo.findAllByHoaDon(hd);
    }

    public HoaDonChiTiet updateHDCT(int soLuong, UUID id){
        HoaDonChiTiet newHDCT = this.repo.findById(id).get();
        GiayTheThaoChiTiet gttct = giayTheThaoChiTietRepository.findById(newHDCT.getGiayTheThaoChiTiet().getId()).get();
        int sl = Integer.parseInt(gttct.getSoLuong());
        if((sl-soLuong)<0){
            HoaDonChiTiet newHDCT2 = new HoaDonChiTiet();
            newHDCT2.setSoLuong(""+sl);
            return newHDCT2;
        }
        newHDCT.setSoLuong(String.valueOf(soLuong));
        newHDCT.setTrangThai(2);
        this.repo.save(newHDCT);
        return newHDCT;
    }

    public boolean checkSoLuongGTTCT(UUID id, int soLuong){
        HoaDonChiTiet newHDCT = this.repo.findById(id).get();
        GiayTheThaoChiTiet gttct = giayTheThaoChiTietRepository.findById(newHDCT.getGiayTheThaoChiTiet().getId()).get();
        int sl = Integer.parseInt(gttct.getSoLuong());
        return (sl-soLuong)<0;
    }

    public void removeHDCTByID(UUID id){
        HoaDonChiTiet newHDCT = this.repo.findById(id).get();
        this.repo.delete(newHDCT);
    }

    public HoaDonChiTiet addHDCT(HoaDonChiTiet hdct){

        HoaDon hd = hdRepo.findById(hdct.getHoaDon().getId()).get();
        hdct.setHoaDon(hd);
        GiayTheThaoChiTiet gttct = giayTheThaoChiTietRepository.findById(hdct.getGiayTheThaoChiTiet().getId()).get();
        hdct.setGiayTheThaoChiTiet(gttct);
        int sl = Integer.parseInt(gttct.getSoLuong());
        int check = repo.countByHoaDonAndGiayTheThaoChiTiet(hd, gttct);
        System.out.println("so luong cua san pham: "+sl);

        if(check!=0) {
            HoaDonChiTiet newHDCT = repo.findByHoaDonAndGiayTheThaoChiTiet(hd, gttct);
            int soLuong = Integer.parseInt(newHDCT.getSoLuong())+Integer.parseInt(hdct.getSoLuong());
            if((sl-soLuong)<0){
                System.out.println("so luong sau khi tru: "+(soLuong-sl));
                return null;
            }
            newHDCT.setSoLuong(String.valueOf(soLuong));
            newHDCT.setTrangThai(2);
            this.repo.save(newHDCT);
            return newHDCT;
        }

        if((sl-Integer.parseInt(hdct.getSoLuong()))<0){
            System.out.println("So luong: " + (1-sl));
            return null;

        }
        System.out.println("So luongsssss: "+hdct.getSoLuong());
        hdct.setSoLuong(hdct.getSoLuong());
        hdct.setGhiChu("N/A");
        hdct.setNgayTao(LocalDateTime.now());
        hdct.setNgaySua(LocalDateTime.now());
        hdct.setTrangThai(1);
        this.repo.save(hdct);
        return hdct;
    }



}
