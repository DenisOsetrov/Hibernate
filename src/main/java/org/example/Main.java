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

        //Створюємо Cars - 3 шт.
//        Car car1 = new Car("OPEL", CarType.PASSENGER, 120, 7000, 2010);
//        session.save(car1);
//        Car car2 = new Car("PEUGEOT", CarType.CARGO, 220, 8000, 2017);
//        session.save(car2);
//        Car car3 = new Car("HUNDAI", CarType.PASSENGER, 180, 17000, 2022);
//        session.save(car3);

        // збереженя сесії
        session.getTransaction().commit();

//        session.beginTransaction();
//
//// Видалення таблиці driverlicense, якщо вона вже існує
//        session.createSQLQuery("DROP TABLE IF EXISTS driverlicense").executeUpdate();
//
//        session.getTransaction().commit();


        //Оновлення користувача:
//                        session.beginTransaction();
//
//                // Завантажуємо користувача з бази даних за його ідентифікатором
//                        User user = session.get(User.class, 1);
//
//                // Змінюємо дані користувача
//                        user.setName("John");
//                        user.setEmail("john@gmail.com");
//
//                // Запускаємо оновлення користувача
//                        session.update(user);
//
//                        session.getTransaction().commit();


        // Видалення користувача:

//                    session.beginTransaction();
//
//            // Завантажуємо користувача з бази даних за його ідентифікатором
//                    User user = session.get(User.class, 1);
//
//            // Видаляємо користувача з бази даних
//                    session.delete(user);
//
//                    session.getTransaction().commit();


        // Робимо запит до БД. 1-й варіант - класичний, 2-й - сучасний
                //List<User> users = session.createNativeQuery("select * from user", User.class).getResultList();
                //System.out.println(users);
//        List<User> users = session.createQuery("select u from User u", User.class).getResultList();
                            // List<User> users2 = session.createQuery("select u.age from User u", User.class).getResultList();
                            // List<User> users3 = session.createQuery("select u.email from User u", User.class).getResultList();
//        System.out.println(users);

//        session.createSQLQuery("ALTER TABLE owners DROP FOREIGN KEY FK9qiq6onfguckj0b2wf725dn2o;").executeUpdate();
//        session.createSQLQuery("DROP TABLE IF EXISTS cars").executeUpdate();
//        session.createSQLQuery("DROP TABLE IF EXISTS owners").executeUpdate();
//        session.createSQLQuery("DROP TABLE IF EXISTS DriverLicense").executeUpdate();
        session.close();
        sessionFactory.close();
        serviceRegistry.close();

    }
}




























