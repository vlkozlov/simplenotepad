package com.vkozlov.simplenotepad.controller;

import com.vkozlov.simplenotepad.domain.Note;
import com.vkozlov.simplenotepad.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String main(Map <String, Object> model) {
        Iterable<Note> notes = noteRepo.findAll();
        model.put("notes", notes);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String priority, Map <String, Object> model) {
        Note note = new Note(text, priority);
        noteRepo.save(note);

        Iterable<Note> notes = noteRepo.findAll();
        model.put("notes", notes);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map <String, Object> model) {
        Iterable<Note> notes;

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByPriority(filter);
        } else {
            notes = noteRepo.findAll();
        }
        model.put("notes", notes);

        return "main";
    }
}
