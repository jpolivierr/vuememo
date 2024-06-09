package com.appvenir.vuememo.domain.note.dto;

import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.users.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDto {
    
    private String title;

    private String description;

    private String content;

    public Note toModel(User user){

        Note note = new Note(
            this.title,
            this.description,
            this.content,
            user
        );
       
        return note;
    }
}
