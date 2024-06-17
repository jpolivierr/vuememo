package com.appvenir.vuememo.domain.notebook.mapper;

import com.appvenir.vuememo.domain.note.mapper.NoteMapper;
import com.appvenir.vuememo.domain.notebook.dto.NoteBookDto;
import com.appvenir.vuememo.domain.notebook.model.NoteBook;

public class NoteBookMapper {

    public static NoteBookDto toDto(NoteBook noteBook){
        return new NoteBookDto(noteBook);
    }

    public static NoteBook toModel(NoteBookDto noteBookDto){
        NoteBook noteBook = new NoteBook();
        noteBook.setTitle(noteBookDto.getTitle());
        noteBook.setNotes(noteBookDto.getNotes().stream().map(NoteMapper::toModel).toList());
        return noteBook;
    }
    
}
