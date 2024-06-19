package com.appvenir.vuememo.domain.notebook.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.mapper.NoteMapper;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.note.repository.NoteRepository;
import com.appvenir.vuememo.domain.notebook.dto.NoteBookDto;
import com.appvenir.vuememo.domain.notebook.mapper.NoteBookMapper;
import com.appvenir.vuememo.domain.notebook.model.NoteBook;
import com.appvenir.vuememo.domain.notebook.repository.NoteBookRepository;
import com.appvenir.vuememo.domain.users.DemoUser;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;
import com.appvenir.vuememo.exception.note.NoteTitleAlreadyExistsException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteBookService {

    private final UserService userService;
    private final NoteBookRepository noteBookRepository;
    private final NoteRepository noteRepository;

    public NoteBook getNoteBookById(Long id){

        Optional<NoteBook> noteBook = noteBookRepository.findById(id);

        return noteBook.orElseThrow( () -> new EntityNotFoundException("Notebook not found"));

    }

    public NoteBookDto saveNoteBook(NoteBookDto noteBookDto){

        User currentUser = userService.findByEmail(DemoUser.get().getEmail());

        NoteBook noteBook = NoteBookMapper.toModel(noteBookDto);

        noteBook.setUser(currentUser);
        
        try{

        return NoteBookMapper.toDto(noteBookRepository.save(noteBook));
        
        } catch (DataIntegrityViolationException e) {
            
            if(e.getCause() instanceof ConstraintViolationException && e.getMessage().contains("Duplicate entry")){
                
                 throw new NoteTitleAlreadyExistsException("The title `" + noteBook.getTitle() + "` already exist.");
            }

            throw e;

        }

    }

    @Transactional
    public NoteDto saveNoteInNoteBook(Long noteBookId, NoteDto noteDto){

            User currentUser = userService.findByEmail(DemoUser.get().getEmail());

            NoteBook currentNoteBook = getNoteBookById(noteBookId);

            Note note = NoteMapper.toModel(noteDto, currentUser);

            note.setNoteBook(currentNoteBook);

            note.setNoteBookId(noteBookId);

            currentNoteBook.getNotes().add(note);

        try{

            noteBookRepository.save(currentNoteBook);

            Note savedNote = noteRepository.findByNoteBookIdAndTitle(noteBookId, note.getTitle());

            return NoteMapper.toDto(savedNote);

        
        } catch (DataIntegrityViolationException e) {
            
            if(e.getCause() instanceof ConstraintViolationException && e.getMessage().contains("Duplicate entry")){
                
                System.out.println(e.getMessage());

                 throw new NoteTitleAlreadyExistsException("The title `" + note.getTitle() + "` already exist.");
            }

            throw e;

        }

    }
    
}
