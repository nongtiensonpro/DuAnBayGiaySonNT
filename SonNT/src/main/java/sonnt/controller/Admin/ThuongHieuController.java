package sonnt.controller.Admin;

import sonnt.model.ThuongHieu;
import sonnt.service.admin.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("thuongHieu")
public class ThuongHieuController {


    private final ThuongHieuService service;

    private ThuongHieu th = new ThuongHieu();

    public ThuongHieuController(ThuongHieuService service) {
        this.service = service;
    }

    @GetMapping()
    public String getIndex(Model model, @RequestParam(name = "page", defaultValue = "1")int page){
        Pageable pageable = PageRequest.of(page-1, 5);
        model.addAttribute("list", service.pagination(pageable));
        return "thuongHieu/index";
    }

    @GetMapping("search")
    public String search(Model model, @RequestParam(name = "page", defaultValue = "1")int page, @RequestParam("name") String name){
        Pageable pageable = PageRequest.of(page-1, 5);
        model.addAttribute("list", this.service.search(pageable, name));
        return "thuongHieu/index";
    }

    @GetMapping("create")
    public String getFormCreate(Model model){

        th.setNgayTao(LocalDate.now().toString());
        th.setNgaySua(LocalDate.now().toString());
        th.setTrangThai(1);
        model.addAttribute("th", th);
        return "thuongHieu/create";
    }

    @PostMapping("create")
    public String add(@Valid @ModelAttribute("dg") ThuongHieu thuongHieu, BindingResult result){
        if(result.hasErrors()){

            return "thuongHieu/create";
        }

        thuongHieu.setNgayTao(th.getNgayTao());
        thuongHieu.setNgaySua(th.getNgaySua());
        this.service.add(thuongHieu);

        return "redirect:/thuongHieu";
    }

//    @GetMapping("{id}")
//    public String delete(@PathVariable("id") UUID id){
//        this.service.delete(id);
//        return "redirect:/thuongHieu";
//    }

    @GetMapping("edit/{id}")
    public String getFormUpdate(Model model, @PathVariable("id") ThuongHieu thuongHieu){
        thuongHieu.setNgaySua(LocalDate.now().toString());
        model.addAttribute("th", thuongHieu);
        model.addAttribute("action", "update/"+thuongHieu.getId());
        return "thuongHieu/edit";
    }

    @PostMapping("/edit/update/{id}")
    public String update(@Valid @ModelAttribute("dg") ThuongHieu thuongHieu, @PathVariable("id") UUID id, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("action", "update/"+id);
            return "thuongHieu/edit";
        }

        this.service.update(thuongHieu, id);

        return "redirect:/thuongHieu";
    }

}
