package sonnt.service.GiayTheThao;

import sonnt.model.GiayTheThao;

import java.util.List;
import java.util.UUID;

public interface GiayTheThaoImpl {

    public List<GiayTheThao> getAll();

    public List<GiayTheThao> getAllWithoutInCTGGCTSP(UUID id);

    public GiayTheThao getOne(UUID id);

}
