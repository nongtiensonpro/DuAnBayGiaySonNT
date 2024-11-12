package sonnt.service.impl;

import sonnt.model.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ThuongHieuImpl {

    public void add(ThuongHieu th);
    public void update(ThuongHieu th, UUID id);
    //    public void delete(UUID id);
    public Page<ThuongHieu> search(Pageable pageable, String name);
    public Page<ThuongHieu> pagination(Pageable pageable);

}
