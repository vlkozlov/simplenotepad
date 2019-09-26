package com.vkozlov.simplenotepad.repo;

import com.vkozlov.simplenotepad.domain.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, Integer> {
    List<Note> findByUser_idAndPriority(Long userid, String priority);
    List<Note> findByUser_id(Long userid);
}
