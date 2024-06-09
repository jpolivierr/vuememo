package com.appvenir.vuememo.domain.note.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
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
    public void saveNote(String userEmail, NoteDto noteDto){

        User user = userService.findByEmail(userEmail);
        try {

            noteRepository.save(noteDto.toModel(user));

        } catch (DataIntegrityViolationException e) {
            
            if(e.getCause() instanceof ConstraintViolationException){
                
                throw new NoteTitleAlreadyExistsException("Title `" + noteDto.getTitle() + "` already exist.");
            }

            throw e;

        }
       
    }
    
}
