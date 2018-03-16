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

            // write code here

        }
        finally {
            sessionFactory.close();
        }

    }
}
