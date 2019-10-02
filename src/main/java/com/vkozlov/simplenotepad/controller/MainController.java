package com.vkozlov.simplenotepad.controller;

import com.vkozlov.simplenotepad.domain.Note;
import com.vkozlov.simplenotepad.domain.User;
import com.vkozlov.simplenotepad.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private NoteRepo noteRepo;

    @GetMapping("/")
    public String start(Map<String, Object> model) {
        return "start";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, @AuthenticationPrincipal User user, Model model) {
        Iterable<Note> notes = noteRepo.findByUser_id(user.getId());

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByUser_idAndPriority(user.getId(), filter);
        } else {
            notes = noteRepo.findByUser_id(user.getId());
        }

        model.addAttribute("notes", notes);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String priority,
            @RequestParam Map <String, Object> model) throws IOException {
        Note note = new Note(text, priority, user);
        noteRepo.save(note);

        Iterable<Note> notes = noteRepo.findByUser_id(user.getId());
        model.put("notes", notes);

        return "main";
    }
}
