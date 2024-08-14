package com.example.mistake_feedback_tracker_application;


import com.example.mistake_feedback_tracker_application.controllers.TrackerFormController;
import com.example.mistake_feedback_tracker_application.models.TrackerItem;
import com.example.mistake_feedback_tracker_application.services.TrackerItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

public class MistakeFeedbackTrackerApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private TrackerItemService trackerItemService;

	@InjectMocks
	private TrackerFormController trackerFormController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(trackerFormController).build();
	}

	@Test
	public void testShowCreateForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/create-tracker"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("new-tracker-item"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("trackerItem"));
	}

	@Test
	public void testCreateTrackerItem() throws Exception {
		TrackerItem trackerItem = new TrackerItem();
		trackerItem.setMistake("Typo");
		trackerItem.setOccurrences("3");
		trackerItem.setFeedback("Needs review");

		when(trackerItemService.save(any(TrackerItem.class))).thenReturn(trackerItem);

		mockMvc.perform(MockMvcRequestBuilders.post("/tracker")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("mistake", "Typo")
				.param("occurrences", "3")
				.param("feedback", "Needs review"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/"));

		verify(trackerItemService, times(1)).save(any(TrackerItem.class));
	}
}


