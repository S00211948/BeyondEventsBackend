package org.beyond.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacilityFeatures {
    VOTING("WHITEBOARD", true),
    SCREEN("TV-SCREEN", true),
    KITCHEN("KITCHEN", true),
    SOCCER_PITCH("SOCCER PITCH", false),
    GYM("GYM", false),
    OCEAN("OCEAN", false);

    private final String value;
    private final boolean isOfficeFeature;
}
