package org.example.eventplanner.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private UUID id;
    private String theme;
    private String title;
    private String status;
    private String location;
    private LocalDateTime dateOfEvent;
}
