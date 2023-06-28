package com.example.batch_mybatis.model;

public class ScoreStatistics {
    private String range;
    private Long numberOfStudents;
    public ScoreStatistics() {}

    public ScoreStatistics(String range, Long numberOfStudents) {
        this.range = range;
        this.numberOfStudents = numberOfStudents;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Long getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Long numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
