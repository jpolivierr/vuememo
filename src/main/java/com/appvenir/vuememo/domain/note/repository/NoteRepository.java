package com.appvenir.vuememo.domain.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appvenir.vuememo.domain.note.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

    @Query(value = "SELECT * FROM notes WHERE notes.id = :noteId AND notes.user_id = :userId", nativeQuery = true)
    Optional<Note> findNoteByUser(Long noteId, Long userId);

    @Query(value = "SELECT * FROM notes WHERE notes.user_id = :userId", nativeQuery = true)
    List<Note> findAllByUser(Long userId);
    
}
