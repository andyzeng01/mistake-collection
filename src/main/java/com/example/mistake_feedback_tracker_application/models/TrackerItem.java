package com.example.mistake_feedback_tracker_application.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tracker_items")
public class TrackerItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mistake;

    private String occurrences;

    private String feedback;

    private Instant createdAt;

    private Instant updatedAt;

    @Override
    public String toString() {
        return String.format("TrackerItem{id=%d, mistake='%s', occurrences='%s',feedback='%s', createdAt='%s', updatedAt='%s'}",
                id, mistake, occurrences, feedback, createdAt, updatedAt);
    }

}
