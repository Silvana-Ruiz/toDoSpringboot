package com.todo.todosystem.model;

public class Metrics {
    private String averageTimeTotal;
    private String averageTimeLowTasks;
    private String averageTimeMediumTasks;
    private String averageTimeHighTasks;


    public Metrics(String averageTimeTotal, String averageTimeLowTasks, String averageTimeMediumTasks, String averageTimeHighTasks) {
        this.averageTimeTotal = averageTimeTotal;
        this.averageTimeLowTasks = averageTimeLowTasks;
        this.averageTimeMediumTasks = averageTimeMediumTasks;
        this.averageTimeHighTasks = averageTimeHighTasks;
    }

    public String getAverageTimeTotal() {
        return this.averageTimeTotal;
    }

    public void setAverageTimeTotal(String averageTimeTotal) {
        this.averageTimeTotal = averageTimeTotal;
    }

    public String getAverageTimeLowTasks() {
        return this.averageTimeLowTasks;
    }

    public void setAverageTimeLowTasks(String averageTimeLowTasks) {
        this.averageTimeLowTasks = averageTimeLowTasks;
    }

    public String getAverageTimeMediumTasks() {
        return this.averageTimeMediumTasks;
    }

    public void setAverageTimeMediumTasks(String averageTimeMediumTasks) {
        this.averageTimeMediumTasks = averageTimeMediumTasks;
    }

    public String getAverageTimeHighTasks() {
        return this.averageTimeHighTasks;
    }

    public void setAverageTimeHighTasks(String averageTimeHighTasks) {
        this.averageTimeHighTasks = averageTimeHighTasks;
    }
}
