package com.appvenir.vuememo.domain.notebook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.notebook.dto.NoteBookDto;
import com.appvenir.vuememo.domain.notebook.service.NoteBookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notebooks")
@AllArgsConstructor
public class NoteBookController {

    private final NoteBookService noteBookService;

    @GetMapping
    public ResponseEntity<List<NoteBookDto>> getAllNoteBooks(){
        return ResponseEntity.ok(noteBookService.getAllNoteBooks());
    }

    @GetMapping("/{notebookId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoteBookDto> getNoteBookById(@PathVariable("notebookId") Long id){
        return ResponseEntity.ok(noteBookService.getNoteBookById(id));
    }

    @PutMapping("/{notebookId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoteBookDto> updateNoteBook(@PathVariable("notebookId") Long id, @RequestBody NoteBookDto noteBookDto)
    {
        return ResponseEntity.ok(noteBookService.updateNoteBook(id, noteBookDto));
    }

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

    @DeleteMapping("/{noteBookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNoteBook(@PathVariable("noteBookId") Long id)
    {
        noteBookService.deleteNoteBook(id);
    }
 
    
}
