package com.appvenir.vuememo.domain.note.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.note.repository.NoteRepository;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;
import com.appvenir.vuememo.exception.note.NoteTitleAlreadyExistsException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    @Transactional
    public void saveNote(User user, NoteDto noteDto){

        User currentUser = userService.findByEmail(user.getEmail());

         handleSaveNote(noteDto, () -> {
            Note note = noteDto.toModel(currentUser);
            noteRepository.save(note);
        });
       
    }
    
    private void handleSaveNote (NoteDto noteDto, Runnable saveFunction){
        try {

            saveFunction.run();

        } catch (DataIntegrityViolationException e) {
            
            if(e.getCause() instanceof ConstraintViolationException){
                
                 throw new NoteTitleAlreadyExistsException("The title `" + noteDto.getTitle() + "` already exist.");
            }

            throw e;

        }
    }
}
