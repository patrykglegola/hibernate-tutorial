package com.luv2code.hibernate.demo.app;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            //create a student object
            System.out.println("Creating new student object... ");
            Student student = new Student("Daffy", "Duck", "daffy@luv2code.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);

            //commit transaction
            session.getTransaction().commit();


            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + student.getId());
            Student readStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + readStudent);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
