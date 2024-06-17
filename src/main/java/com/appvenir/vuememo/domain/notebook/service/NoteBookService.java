package com.appvenir.vuememo.domain.notebook.service;

import org.springframework.stereotype.Service;

import com.appvenir.vuememo.domain.notebook.dto.NoteBookDto;
import com.appvenir.vuememo.domain.notebook.mapper.NoteBookMapper;
import com.appvenir.vuememo.domain.notebook.model.NoteBook;
import com.appvenir.vuememo.domain.notebook.repository.NoteBookRepository;
import com.appvenir.vuememo.domain.users.DemoUser;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteBookService {

    private final UserService userService;
    private final NoteBookRepository noteBookRepository;

    public NoteBookDto saveNoteBook(NoteBookDto noteBookDto){

        User currentUser = userService.findByEmail(DemoUser.get().getEmail());

        NoteBook noteBook = NoteBookMapper.toModel(noteBookDto);
        noteBook.setUser(currentUser);
        
        return NoteBookMapper.toDto(noteBookRepository.save(noteBook));

    }
    
}
