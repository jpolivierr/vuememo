package com.appvenir.vuememo.domain.notebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appvenir.vuememo.domain.notebook.model.NoteBook;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {

    List<NoteBook> findAllByUserId(Long id);
    
}