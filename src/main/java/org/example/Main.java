package org.example;

import models.Car;
import models.DriverLicense;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
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


//        User user1 = new User("Vasya", 16, "vasay@gmail.com", Arrays.asList("Java","Js"), Gender.MALE);
//        session.save(user1);
//        User user2 = new User("Anna", 32, "anna@gmail.com", Arrays.asList("Http","TypeScript"), Gender.FEMALE);
//        session.save(user2);
//        User user3 = new User("Max", 46, "maax@gmail.com", Arrays.asList("CSS","React"), Gender.MALE);
//        session.save(user3);


        // збереженя сесії
        session.getTransaction().commit();


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

        session.close();
        sessionFactory.close();
        serviceRegistry.close();

    }
}




























