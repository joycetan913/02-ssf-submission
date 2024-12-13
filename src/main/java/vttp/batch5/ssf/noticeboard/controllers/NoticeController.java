package vttp.batch5.ssf.noticeboard.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.pattern.DateConverter;
import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.models.Notice;

// Use this class to write your request handlers
@Controller
@RequestMapping
public class NoticeController {

    @GetMapping("/")
    public String getNotice(Model model){
     
        Notice n = new Notice();
        model.addAttribute("notice", n);

        return "notice";
    }

    @PostMapping("/notice")
    public String postNotice(Model model, @Valid @RequestBody MultiValueMap<String, String> form, BindingResult result){

        if (result.hasErrors())
            return "redirect:/";
            
        String title = form.getFirst("title");
        String poster = form.getFirst("poster");
        String postDate = form.getFirst("postDate");
        String categories = form.getFirst("categories");
        String text = form.getFirst("text");

        model.addAttribute("notice", new Notice());

        return "notice";

    }
}
  
