package org.example.eventplanner.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
}
