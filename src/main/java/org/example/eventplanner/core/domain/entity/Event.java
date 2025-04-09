package org.example.eventplanner.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventplanner.core.domain.entity.enums.Status;
import org.example.eventplanner.core.domain.entity.enums.Themes;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private UUID id;
    private String title;
    private Themes theme;
    private Status status;
    private String location;
    private LocalDateTime dateOfEvent;
}
