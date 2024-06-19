package com.appvenir.vuememo.domain.notebook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.notebook.dto.NoteBookDto;
import com.appvenir.vuememo.domain.notebook.service.NoteBookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/noteBooks")
@AllArgsConstructor
public class NoteBookController {

    private final NoteBookService noteBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NoteBookDto> saveNoteBook(@RequestBody NoteBookDto noteBookDto){
        return ResponseEntity.ok(noteBookService.saveNoteBook(noteBookDto));
    }

    @PostMapping("/{noteBookId}/notes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoteDto> saveNoteInNoteBook(@PathVariable("noteBookId") Long id, @RequestBody NoteDto noteDto)
    {
        return ResponseEntity.ok(noteBookService.saveNoteInNoteBook(id, noteDto));
    }

    
}
