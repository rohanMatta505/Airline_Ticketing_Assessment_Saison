package com.example.airline_ticketing_assessment_saison.entity;

public class DayOfWeek {
    private EnumSet<Day> days;
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }
}
