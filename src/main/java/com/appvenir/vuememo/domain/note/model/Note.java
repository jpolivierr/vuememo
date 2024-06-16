package com.appvenir.vuememo.domain.note.model;

import com.appvenir.vuememo.domain.baseEntity.BaseEntity;
import com.appvenir.vuememo.domain.users.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note extends BaseEntity{

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;
    
}
