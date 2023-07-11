package org.example;

import models.Car;
import models.CarType;
import models.DriverLicense;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;


//Owner (Власник)
//    id,
//    name
//    List<Car>
//    DriveLicense

// Car
//    id
//    model,
//    Type (ENUM)
//    power,
//    price,
//    year

//DriveLicense
//    id
//    series
//
//За допомоги хібернейту реалізувати наступну структуру - зв'язок unidirectional (@OneToMany)!

public class Main {
    public static void main(String[] args) {
        // налаштовуємо базову конфігурацію. Побудуємо об'єкт:
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry) // Бере дані з класів для створення таблиці в MSQL
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(DriverLicense.class)
                .getMetadataBuilder()
                .build();

        // побудуємо "фабрику" сесій:
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        // відкриємо 1 сесію з базою даний:
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        //Створюємо Owners - 3 шт.
        List<Car> cars1 = new ArrayList<>();
        cars1.add(new Car("OPEL", CarType.PASSENGER, 120, 7000, 2010));
        Owner owner1 = new Owner("Oleg", cars1, new DriverLicense("12343223", "ER"));
        session.save(owner1);

        List<Car> cars2 = new ArrayList<>();
        cars2.add(new Car("PEUGEOT", CarType.CARGO, 220, 8000, 2017));
        Owner owner2 = new Owner("Anna", cars2, new DriverLicense("23343255", "OP"));
        session.save(owner2);

        List<Car> cars3 = new ArrayList<>();
        cars3.add(new Car("HUNDAI", CarType.PASSENGER, 180, 17000, 2022));
        Owner owner3 = new Owner("Tom", cars3, new DriverLicense("65343244", "FE"));
        session.save(owner3);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        serviceRegistry.close();

    }
}




























