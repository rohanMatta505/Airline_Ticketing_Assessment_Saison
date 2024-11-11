package com.example.airline_ticketing_assessment_saison.entity;

public class DayOfWeek {
    private EnumSet<Day> days;

    // Enum representing the days of the week
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    // Constructor to initialize the days
    public DayOfTheWeek() {
        this.days = EnumSet.noneOf(Day.class);
    }
}
