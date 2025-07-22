package com.selman.scms.dto;

public class DoctorAvailableTimeDTO {
    private String day;
    private String timeSlot;

    public DoctorAvailableTimeDTO(String day, String timeSlot) {
        this.day = day;
        this.timeSlot = timeSlot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
