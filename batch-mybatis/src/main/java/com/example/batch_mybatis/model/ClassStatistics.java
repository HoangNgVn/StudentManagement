package com.example.batch_mybatis.model;

public class ClassStatistics {
    private String classId;
    private int totalStudents;
    private double studentPercentage;
    private double averageScore;
    private String scoreRanking;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public double getStudentPercentage() {
        return studentPercentage;
    }

    public void setStudentPercentage(double studentPercentage) {
        this.studentPercentage = studentPercentage;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getScoreRanking() {
        return scoreRanking;
    }

    public void setScoreRanking(String scoreRanking) {
        this.scoreRanking = scoreRanking;
    }

    @Override
    public String toString() {
        return "ClassStatistics{" +
                "classId='" + classId + '\'' +
                ", totalStudents=" + totalStudents +
                ", studentPercentage=" + studentPercentage +
                ", averageScore=" + averageScore +
                ", scoreRanking='" + scoreRanking + '\'' +
                '}';
    }
}
