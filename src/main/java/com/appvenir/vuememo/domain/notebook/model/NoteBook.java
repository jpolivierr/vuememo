package com.appvenir.vuememo.domain.notebook.model;

import java.util.List;

import com.appvenir.vuememo.domain.baseEntity.BaseEntity;
import com.appvenir.vuememo.domain.note.model.Note;
import com.appvenir.vuememo.domain.users.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "noteBooks", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "title"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteBook extends BaseEntity{

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany( mappedBy = "noteBook", 
                cascade = CascadeType.ALL,
                orphanRemoval = true
    )
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;
    
}
