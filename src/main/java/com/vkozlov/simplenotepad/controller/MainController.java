package com.vkozlov.simplenotepad.controller;

import com.vkozlov.simplenotepad.domain.Note;
import com.vkozlov.simplenotepad.domain.User;
import com.vkozlov.simplenotepad.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private NoteRepo noteRepo;

    @GetMapping("/")
    public String start(Map<String, Object> model) {
        return "start";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user, Map <String, Object> model) {
        Iterable<Note> notes = noteRepo.findByUser_id(user.getId());
        model.put("notes", notes);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String priority,
                      Map <String, Object> model) {
        Note note = new Note(text, priority, user);
        noteRepo.save(note);

        Iterable<Note> notes = noteRepo.findByUser_id(user.getId());
        model.put("notes", notes);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@AuthenticationPrincipal User user, @RequestParam String filter, Map <String, Object> model) {
        Iterable<Note> notes;

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByUser_idAndPriority(user.getId(), filter);
        } else {
            notes = noteRepo.findByUser_id(user.getId());
        }
        model.put("notes", notes);

        return "main";
    }
}
