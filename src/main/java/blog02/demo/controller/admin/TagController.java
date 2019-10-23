package blog02.demo.controller.admin;

import blog02.demo.mapperimp.Tlagmapper;
import blog02.demo.pojo.Tlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TagController {

    @Autowired
    private Tlagmapper tlagmapper;

    // 显示标签页面
    @GetMapping("/admin/tags")
    public String tags(Model model) {
        model.addAttribute("page",tlagmapper.selectTlagAll());
        return "admin/tags";
    }

    // 显示标签新增页面
    @GetMapping("/admin/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tlag());
        return "admin/tags-input";
    }

    // 显示标签编辑页面
    @GetMapping("/admin/tags/{id}/input")
    public String editInput(@PathVariable int id, Model model) {
        model.addAttribute("tag", tlagmapper.selecttlagbyId(id));
        return "admin/tags-input";
    }


    // 标签新增
    @PostMapping("/admin/tags")
    public String post(Tlag tag, BindingResult result, RedirectAttributes attributes) {
        Tlag tag1 = tlagmapper.selectTlagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int t = tlagmapper.insertTlag(tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    // 标签修改
    @PostMapping("/admin/tags/{id}")
    public String editPost(Tlag tag, BindingResult result, RedirectAttributes attributes) {
        Tlag tag1 = tlagmapper.selectTlagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int t = tlagmapper.updateTlag(tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    // 标签删除
    @GetMapping("/admin/tags/{id}/delete")
    public String delete(Tlag tag,RedirectAttributes attributes) {
        int t =tlagmapper.deleteTlag(tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/tags";
    }

}
