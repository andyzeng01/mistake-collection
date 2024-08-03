package com.example.mistake_feedback_tracker_application.controllers;

import com.example.mistake_feedback_tracker_application.services.TrackerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private TrackerItemService trackerItemService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("trackerItems", trackerItemService.getAll());
        return modelAndView;
    }

}
