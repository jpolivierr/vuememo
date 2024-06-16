package com.appvenir.vuememo.domain.note.mapper;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.users.model.User;

public class NoteMapper {

    public static NoteDto toDto(Note note){
        return new NoteDto(note);
    }

    public static Note toModel(NoteDto noteDto, User user){
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setDescription(noteDto.getDescription());
        note.setContent(noteDto.getContent());
        note.setUser(user);
        return note;
    }
}
