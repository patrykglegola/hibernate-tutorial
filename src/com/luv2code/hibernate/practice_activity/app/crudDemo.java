package com.luv2code.hibernate.practice_activity.app;

import com.luv2code.hibernate.practice_activity.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class crudDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {

            createAndSaveSomeObject(session);
            
        }
        finally {
            sessionFactory.close();
        }

    }

    private static void createAndSaveSomeObject(Session session) {
        Employee employee1 = new Employee("Adam", "Smith", "Pear");
        Employee employee2 = new Employee("Michael", "Roberts", "Megahard");

        session.beginTransaction();
        session.save(employee1);
        session.save(employee2);
        session.getTransaction().commit();
    }

}
