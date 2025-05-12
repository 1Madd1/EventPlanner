package org.example.eventplanner.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventplanner.entity.enums.Status;
import org.example.eventplanner.entity.enums.Theme;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private UUID id;
    private Theme theme;
    private String title;
    private Status status;
    private String location;
    private LocalDateTime dateOfEvent;
}
