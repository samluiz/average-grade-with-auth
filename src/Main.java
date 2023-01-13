import model.entities.Student;
import model.exceptions.WrongCredentialsException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> grades = new ArrayList<>();

        try {
            System.out.println("Inform the student data:");
            System.out.print("Name (login): ");
            String name = sc.nextLine();
            System.out.print("Password (don't forget this password): ");
            String password = sc.nextLine();
            Student student = new Student(name, password, grades);

            boolean isLogged = false;
            boolean isRobot = true;

            while(!isLogged && isRobot) {
                System.out.println("Please login using the credentials you just typed.");
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                System.out.println("Time for verification!");
                System.out.print("Type a positive integer number: ");
                int number = sc.nextInt();

                if (number < 0) {
                    System.out.println("Failed verification. Try Again.");
                    sc.nextLine();
                } else {
                    isRobot = false;
                }

                try {
                    if (student.auth(login, pass) && !isRobot) {
                        isLogged = true;
                        System.out.print("How much grades do you wanna get the ratio: ");
                        int n = sc.nextInt();
                        System.out.println("Please inform the student grades: ");
                        for (int i = 0; i < n; i++) {
                            student.addGrade(sc.nextDouble());
                        }
                        System.out.println("Student: " + student.getName() + "\nAverage grade: " + student.ratio());
                    }
                } catch (WrongCredentialsException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Error: you must type a number.");
        }


    }
}