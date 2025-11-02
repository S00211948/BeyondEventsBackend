package org.beyond.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationFeatures {
    VOTING("VOTING"),
    TIMETABLE("TIMETABLE"),
    COMMENTS("COMMENTS"),
    BRACKETS("BRACKETS");
    private final String value;

}