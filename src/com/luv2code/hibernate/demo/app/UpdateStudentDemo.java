package com.luv2code.hibernate.demo.app;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key and update his name
            System.out.println("\nGetting student with id: " + studentId);
            Student student = session.get(Student.class, studentId);
            System.out.println("Updating student...");
            student.setFirstName("Scooby");

            //commit transaction
            session.getTransaction().commit();

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("Update email for all students");
            session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
