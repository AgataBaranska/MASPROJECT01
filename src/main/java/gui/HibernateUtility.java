package gui;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    public static SessionFactory factory;

    private HibernateUtility(){
    }

    public static synchronized SessionFactory getSessionFactory(){
        if(factory == null){
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Training.class)
                    .addAnnotatedClass(RodoForm.class)
                    .addAnnotatedClass(Receptionist.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Optometrist.class)
                    .addAnnotatedClass(LensesCorrection.class)
                    .addAnnotatedClass(GlassesCorrection.class)
                    .addAnnotatedClass(ContactLense.class)
                    .addAnnotatedClass(AppointmentCart.class)
                    .addAnnotatedClass(Appointment.class)
                    .addAnnotatedClass(Address.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(builder.build());
        }
        return factory;
    }
}
