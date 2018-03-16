package com.luv2code.hibernate.practice_activity.app;

import com.luv2code.hibernate.practice_activity.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class crudDemo {

    private static Session session;

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        try {
            //createAndSaveSomeObject();
            //displayObjectWithId(1);
            displayObjectWithHQLQuery("Megahard");

        }
        finally {
            sessionFactory.close();
        }

    }

    private static void createAndSaveSomeObject() {
        Employee employee1 = new Employee("Adam", "Smith", "Pear");
        Employee employee2 = new Employee("Michael", "Roberts", "Megahard");

        session.beginTransaction();
        session.save(employee1);
        session.save(employee2);
        session.getTransaction().commit();
    }

    private static void displayObjectWithId(int id) {
        session.beginTransaction();
        Employee readEmployee = session.get(Employee.class, id);
        System.out.println("\nEmployee from database with id " + id + ": \n" + readEmployee);
        session.getTransaction().commit();
    }

    private static void displayObjectWithHQLQuery(String companyName) {
        session.beginTransaction();
        System.out.println("\n");
        List<Employee> employees = session
                .createQuery("from Employee e where e.company='" + companyName + "'").getResultList();
        System.out.println("\nList of employees whose company name is " + companyName + ": ");
        display(employees);
        session.getTransaction().commit();
    }

    private static void display(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
