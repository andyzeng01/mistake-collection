package com.example.mistake_feedback_tracker_application.controllers;

import com.example.mistake_feedback_tracker_application.models.TrackerItem;
import com.example.mistake_feedback_tracker_application.services.TrackerItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrackerFormController {

    @Autowired
    private TrackerItemService trackerItemService;

    @GetMapping("/create-tracker")
    public String showCreateForm(TrackerItem trackerItem) {
        return "new-tracker-item";
    }

    @PostMapping("/tracker")
    public String createTrackerItem(@Valid TrackerItem trackerItem, BindingResult result, Model model) {
        TrackerItem item = new TrackerItem();
        item.setMistake(trackerItem.getMistake());
        item.setOccurrences(trackerItem.getOccurrences());
        item.setFeedback(trackerItem.getFeedback());

        trackerItemService.save(trackerItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TrackerItem trackerItem = trackerItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        trackerItemService.delete(trackerItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TrackerItem trackerItem = trackerItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("tracker", trackerItem);
        return "edit-tracker-item";
    }

    @PostMapping("/tracker/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TrackerItem trackerItem, BindingResult result, Model model) {

        TrackerItem item = trackerItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setMistake(trackerItem.getMistake());
        item.setOccurrences(trackerItem.getOccurrences());
        item.setFeedback(trackerItem.getFeedback());

        trackerItemService.save(item);

        return "redirect:/";
    }
}
