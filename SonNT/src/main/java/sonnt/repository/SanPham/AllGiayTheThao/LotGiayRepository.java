package sonnt.repository.SanPham.AllGiayTheThao;

import sonnt.model.LotGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface LotGiayRepository extends JpaRepository<LotGiay, UUID> {
}
