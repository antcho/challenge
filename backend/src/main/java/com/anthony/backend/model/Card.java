package com.anthony.backend.model;

import java.time.LocalDate;
import java.util.UUID;

public interface Card {
    UUID getId();
    Float getInitialValue();
    Float getCurrentValue();
    LocalDate getDistributionDate();
}
