package com.example.mistake_feedback_tracker_application.services;

import com.example.mistake_feedback_tracker_application.models.TrackerItem;
import com.example.mistake_feedback_tracker_application.repositories.TrackerItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TrackerItemService {

    @Autowired
    private TrackerItemRepository trackerItemRepository;

    public Iterable<TrackerItem> getAll() {
        return trackerItemRepository.findAll();
    }

    public Optional<TrackerItem> getById(Long id) {
        return trackerItemRepository.findById(id);
    }

    public TrackerItem save(TrackerItem trackerItem) {
        if (trackerItem.getId() == null) {
            trackerItem.setCreatedAt(Instant.now());
        }

        trackerItem.setUpdatedAt(Instant.now());

        return trackerItemRepository.save(trackerItem);
    }

    public void delete(TrackerItem trackerItem) {
        trackerItemRepository.delete(trackerItem);
    }




}
