package com.appvenir.vuememo.domain.note.dto;

import com.appvenir.vuememo.domain.note.model.Note;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {

    private Long id;
    
    private String title;

    private String description;

    private String content;

    public NoteDto(Note note){
        this.id = note.getId();
        this.title = note.getTitle();
        this.description = note.getDescription();
        this.content = note.getContent();
    }

}
