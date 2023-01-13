package model.entities;

import model.exceptions.WrongCredentialsException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {
    private String id;
    private String name;
    private String password;
    private List<Double> grades;

    public Student() {
        this.id = createID();
    }

    public static String createID()
    {
        AtomicInteger idCounter = new AtomicInteger();
        return String.valueOf(idCounter.getAndIncrement());
    }

    public Student(String name, String password, List<Double> grades) {
        this.id = createID();
        this.name = name;
        this.password = password;
        this.grades = grades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(Double grade) {
        grades.add(grade);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean auth(String login, String password) throws WrongCredentialsException {
        if (login.equals(this.name) && password.equals(this.password)) {
            return true;
        } else {
            throw new WrongCredentialsException("Wrong credentials.");
        }
    }

    public Double ratio() {
        Double ratio = 0.0;
        for (Double grade : grades) {
            ratio += grade;
        }
        return ratio / grades.size();
    }
}
