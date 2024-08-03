package com.example.mistake_feedback_tracker_application.repositories;

import com.example.mistake_feedback_tracker_application.models.TrackerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerItemRepository extends JpaRepository<TrackerItem, Long> {
}
