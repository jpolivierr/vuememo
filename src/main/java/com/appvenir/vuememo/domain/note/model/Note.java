package com.appvenir.vuememo.domain.note.model;

import com.appvenir.vuememo.domain.baseEntity.BaseEntity;
import com.appvenir.vuememo.domain.notebook.model.NoteBook;
import com.appvenir.vuememo.domain.users.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "notes" , uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "title"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Note extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notebook_id", nullable = true)
    private NoteBook noteBook;

    @Column(name = "notebook_id", insertable = false, updatable = false)
    private Long noteBookId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;
    
}
