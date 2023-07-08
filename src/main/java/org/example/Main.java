package org.example;

import models.Gender;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

// 1. Створити новий проєкт, поставити збірку Maven  і мову Java
// 2. Встановлюємо <dependency> в pom.xml з сайту mvnrepository.
         // Зберізається все в C:\Users\dvose\.m2, цю папку можна чистити
// 3. В папці resources -> new file -> hibernate.cfg.xml
        // Налаштовуємо конфігурацію файлу! є в цьому файлі
// 4. Працюємо з Main!
// 5. Створюємо package - models -> User -> наповнемо класс полями, побудуємо getter i setter i toString i constructor
                                       //-> або додаємо бібл. Lombok -> @Data + @NoArgsConstructor
// 6. Зберігаємо дані в БД -> створюємо new User тільки в межах session.beginTransaction();
            // - запускаємо проєкт і перегружаємо(Refresh) БД -> маємо 2 таблички: user i user_skill
// 7. Дістаємо дані з БД (поза межами session.beginTransaction();)

public class Main {
    public static void main(String[] args) {
        // налаштовуємо базову конфігурацію. Побудуємо об'єкт:
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry) // Бере дані з класів для створення таблиці в MSQL
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();

        // побудуємо "фабрику" сесій:
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        // відкриємо 1 сесію з базою даний:
        Session session = sessionFactory.openSession();

        //
        session.beginTransaction();
        // опис дій з БД: S
                // session.save()
                // session.update()
                // session.delete()

        User user1 = new User("Vasya", 16, "vasay@gmail.com", Arrays.asList("Java","Js"), Gender.MALE);
        session.save(user1);
        User user2 = new User("Anna", 32, "anna@gmail.com", Arrays.asList("Http","TypeScript"), Gender.FEMALE);
        session.save(user2);
        User user3 = new User("Max", 46, "maax@gmail.com", Arrays.asList("CSS","React"), Gender.MALE);
        session.save(user3);


        // Приклад/ Example
                // Session session = sessionFactory.openSession();
                //Transaction tx = null;
                //try {
                //    tx = session.beginTransaction();
                //
                //    // Виконання операцій з базою даних (збереження, оновлення, видалення, запити)
                //
                //    tx.commit();
                //} catch (Exception e) {
                //    if (tx != null) {
                //        tx.rollback();// session.getTransaction().rollback(), і зміни не будуть збережені.
                //    }
                //    e.printStackTrace();
                //} finally {
                //    session.close();
                //}
                //В цьому прикладі ми відкриваємо сеанс, починаємо транзакцію, виконуємо операції з базою даних, комітуємо транзакцію в разі успіху або відкатуємо її в разі помилки, і нарешті закриваємо сеанс.

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
        List<User> users = session.createQuery("select u from User u", User.class).getResultList();
                            // List<User> users2 = session.createQuery("select u.age from User u", User.class).getResultList();
                            // List<User> users3 = session.createQuery("select u.email from User u", User.class).getResultList();
        System.out.println(users);

        session.close();
        sessionFactory.close();
        serviceRegistry.close();

    }
}




























