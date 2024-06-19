package com.appvenir.vuememo.domain.note.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.service.NoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable("id") Long id){
        return ResponseEntity.ok().body( noteService.getNote(id));
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes(){
        return ResponseEntity.ok().body( noteService.getAllNotes());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto){

    NoteDto newNoteDto = noteService.saveNote(noteDto);
    return ResponseEntity.ok(newNoteDto);

    }

   @PutMapping
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto){

    NoteDto newNoteDto = noteService.updateNote(noteDto);
    return ResponseEntity.ok(newNoteDto);

   }

   @DeleteMapping("/{noteId}")
   @ResponseStatus(HttpStatus.OK)
   public void deleteNote(@PathVariable("noteId") Long id){
 
     noteService.deleteNote(id);

   }
    
}
