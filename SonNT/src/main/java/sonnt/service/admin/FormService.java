package sonnt.service.admin;


import sonnt.model.Form;
import sonnt.repository.SanPham.AllGiayTheThao.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getAll() {
        return formRepository.findAll();
    }

    public Form getOne(UUID id) {
        return formRepository.findById(id).orElse(null);
    }

    public void save(Form form) {
        this.formRepository.save(form);
    }

    public void delete(UUID id) {
        this.formRepository.deleteById(id);
    }

    public List<Form> searchByTen(String tenForm) {
        return formRepository.findByTenFormContainingOrderById(tenForm);
    }

}
