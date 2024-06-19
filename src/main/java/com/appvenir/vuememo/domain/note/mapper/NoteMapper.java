package com.appvenir.vuememo.domain.note.mapper;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.users.model.User;

public class NoteMapper {

    public static NoteDto toDto(Note note){
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setDescription(note.getDescription());
        noteDto.setContent(note.getContent());
        noteDto.setNoteBookId(note.getNoteBookId());
        return noteDto;
    }

    public static Note toModel(NoteDto noteDto){
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setDescription(noteDto.getDescription());
        note.setContent(noteDto.getContent());
        return note;
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
