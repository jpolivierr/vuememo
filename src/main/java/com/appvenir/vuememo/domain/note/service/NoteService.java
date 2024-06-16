package com.appvenir.vuememo.domain.note.service;

import com.appvenir.vuememo.domain.note.mapper.NoteMapper;
import com.appvenir.vuememo.domain.users.DemoUser;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.appvenir.vuememo.domain.note.dto.NoteDto;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.note.repository.NoteRepository;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;
import com.appvenir.vuememo.exception.note.NoteNotFoundException;
import com.appvenir.vuememo.exception.note.NoteTitleAlreadyExistsException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    @Transactional
    public NoteDto saveNote(NoteDto noteDto){

        User currentUser = userService.findByEmail(DemoUser.get().getEmail());

         return handleSaveNote(noteDto, () -> {
             Note note = NoteMapper.toModel(noteDto, currentUser);
            return NoteMapper.toDto(noteRepository.save(note));
        });

    }

    public NoteDto getNote(Long id){

        User currentUser = userService.findByEmail(DemoUser.get().getEmail());

        Optional<Note> currentNote = noteRepository.findNoteByUser(id, currentUser.getId());

        Note note = currentNote.orElseThrow( () -> new NoteNotFoundException());

        return NoteMapper.toDto(note);

    }

    public List<NoteDto> getAllNotes(){

        User currentUser = userService.findByEmail(DemoUser.get().getEmail());

        List<Note> notes = noteRepository.findAllByUser(currentUser.getId());

        return notes.stream().map(NoteMapper::toDto).toList();

    }

    @Transactional
    public NoteDto updateNote(NoteDto noteDto){

         return handleSaveNote(noteDto, () -> {
             Note currentNote = findNote(noteDto.getId());
             currentNote.setTitle(noteDto.getTitle());
             currentNote.setDescription(noteDto.getDescription());
             currentNote.setContent(noteDto.getContent());
            return NoteMapper.toDto(noteRepository.save(currentNote));
        });

    }

    public Note findNote(Long id){
        return noteRepository.findById(id).orElseThrow( () -> new NoteNotFoundException());
    }

    public void deleteNote(Long id){
        
        Optional<Note> currentNote = noteRepository.findById(id);
        if(!currentNote.isPresent())
             throw new NoteNotFoundException("A note with id `" + id +"` was note found");
 
         noteRepository.deleteById(id);
 
     }

    @Transactional
    private NoteDto handleSaveNote (NoteDto noteDto, Supplier<NoteDto> saveFunction){
        try {

            return saveFunction.get();

        } catch (DataIntegrityViolationException e) {
            
            if(e.getCause() instanceof ConstraintViolationException){
                
                 throw new NoteTitleAlreadyExistsException("The title `" + noteDto.getTitle() + "` already exist.");
            }

            throw e;

        }
    }

}
