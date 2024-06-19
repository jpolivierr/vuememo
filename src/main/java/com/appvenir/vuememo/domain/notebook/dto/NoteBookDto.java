package com.appvenir.vuememo.domain.notebook.dto;

import java.util.ArrayList;
import java.util.List;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.mapper.NoteMapper;
import com.appvenir.vuememo.domain.notebook.model.NoteBook;
import com.appvenir.vuememo.exception.note.NoteNotFoundException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteBookDto {

    private Long id;

    private String title;

    private List<NoteDto> notes = new ArrayList<>();

    public NoteBookDto(NoteBook noteBook){
        this.id = noteBook.getId();
        this.title = noteBook.getTitle();
        this.notes = noteBook.getNotes().stream()
                                        .map(NoteMapper::toDto)
                                        .toList();
    }

    public NoteDto getNoteByTitle(String title){
        return notes.stream()
                    .filter( n -> n.getTitle().equals(title))
                    .findFirst()
                    .orElseThrow( () -> new NoteNotFoundException("A note was not found"));
    }
    
    
}
