package org.example;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


// 1. Створити новий проєкт, поставити збірку Maven  і мову Java
// 2. Встановлюємо <dependency> в pom.xml з сайту mvnrepository.
         // Зберізається все в C:\Users\dvose\.m2, цю папку можна чистити
// 3. В папці resources -> new file -> hibernate.cfg.xml
        // Налаштовуємо конфігурацію файлу! є в цьому файлі
// 4. Працюємо з Main!
// 5. Створюємо package - models -> User -> наповнемо класс полями, побудуємо getter i setter i toString i constructor
                                       //-> або додаємо бібл. Lombok -> @Data + @NoArgsConstructor
// 6. Зберіг// - запускаємо проєкт і перегружаємо(Refresh) БД -> маємо 2 таблички: user i user_skillаємо дані в БД -> створюємо new User тільки в межах session.beginTransaction();

// 7. Дістаємо дані з БД (поза межами session.beginTransaction();)

public class Main {
    public static void main(String[] args) {
        // налаштовуємо базову конфігурацію. Побудуємо об'єкт:
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry) // Бере дані з класів для створення таблиці в MSQL
                .addAnnotatedClass(FinanceTransaction.class)
                .getMetadataBuilder()
                .build();

        // побудуємо "фабрику" сесій:
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        // відкриємо 1 сесію з базою даний:
        Session session = sessionFactory.openSession();

        // початок транзакції
        session.beginTransaction();

        // Створення, збереження і занесення даних в БД
        session.save(
                new FinanceTransaction(
                        TransactionType.DEBT,
                        Status.PENDING,
                        123.30,
                        true,
                        "lorem ipsum")
        );
        session.save(
                new FinanceTransaction(
                        TransactionType.CREDIT,
                        Status.REJECT,
                        465.11,
                        false,
                        "lorem ipsum 2")
        );
        session.save(
                new FinanceTransaction(
                        TransactionType.DEBT,
                        Status.COMPLETE,
                        78.805,
                        true,
                        "lorem ipsum 3")
        );

        // Пошук в БД
        System.out.println("*****************");  // розподілювач
        List<FinanceTransaction> transactions = session.createQuery("select t from FinanceTransaction t", FinanceTransaction.class).getResultList();
        System.out.println(transactions);  // знайде і покаже всі транзакції

        System.out.println("*****************");  // розподілювач
        FinanceTransaction transaction1 = session.find(FinanceTransaction.class, 1);
        System.out.println(transaction1);  // знайде транзакцію з id=1


        // оновлення даних: можна через save - якщо id співпадають та через update!
        transaction1.setSum(999999);
        session.update(transaction1);


        // збереженя сесії
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        serviceRegistry.close();

    }
}




























