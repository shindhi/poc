package com.invillia.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Team(", ")")
                .add("id = " + id)
                .add("name = " + name)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setName(String name) {
        this.name = name;
    }
}
