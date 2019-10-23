package blog02.demo.controller.admin;

import blog02.demo.mapperimp.Typemapper;
import blog02.demo.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TypeController {

    @Autowired
    private Typemapper typemapper;

    // 显示type页面
    @GetMapping("/admin/types")
    public String types(Model model) {
        model.addAttribute("page",typemapper.selectTypeAll());
        return "admin/types";
    }

    // 显示type 新增页面
    @GetMapping("/admin/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    // 显示type 修改页面
    @GetMapping("/admin/types/{id}/input")
    public String editInput(@PathVariable int id, Model model) {
        Type t = typemapper.selecttypebyID(id);
        model.addAttribute("type", t);
        return "admin/types-input";
    }

    @PostMapping("/admin/types")
    public String post(Type type, BindingResult result, RedirectAttributes attributes) {
        Type type1 = typemapper.selectTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int t = typemapper.insertType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/admin/types/{id}")
    public String editPost(Type type, BindingResult result,RedirectAttributes attributes) {
        Type type1 = typemapper.selectTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int t = typemapper.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/admin/types/{id}/delete")
    public String delete(Type type,RedirectAttributes attributes) {
        int t =typemapper.deleteType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/types";
    }

}
