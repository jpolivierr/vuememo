package com.appvenir.vuememo.domain.note.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.service.NoteService;
import com.appvenir.vuememo.domain.users.model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNote(@RequestBody NoteDto noteDto){

        User user = new User();
        user.setEmail("jp@gmail.com");
        noteService.saveNote(user, noteDto);

    }
    
}
