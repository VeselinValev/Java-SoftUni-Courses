package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String[] studentInfo = reader.readLine().split(" ");
            Student student = new Student(studentInfo[0], studentInfo[1], studentInfo[2]);
            String[] workerInfo = reader.readLine().split(" ");
            double weekPay = Double.parseDouble(workerInfo[2]);
            double hours = Double.parseDouble(workerInfo[3]);
            Worker worker = new Worker(workerInfo[0], workerInfo[1], weekPay, hours);
            System.out.printf("First Name: %s\nLast Name: %s\nFaculty number: %s\n\n", student.getFirstName(), student.getLastName(), student.getFacultyNumber());
            System.out.printf("First Name: %s\nLast Name: %s\nWeek Salary: %.2f\nHours per day: %.2f\nSalary per hour: %.2f\n",
                    worker.getFirstName(), worker.getLastName(), worker.getWeekSalary(), worker.getHoursPerDay(), worker.claculateSalaryPerHour());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

}
