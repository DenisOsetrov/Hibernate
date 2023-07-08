package models;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
//@Table(name = "user_table") переіменує нашу табличку user в user_table в БД, але в коді ми працюємо з user
public class User {

    @Id           // Ці дві анотації будують ID з унікальним значенням
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY - звичайний авто інкремент (++)
    private int id;
    @Column(name="username") //  В БД колонка name матиме назву username
    private String name;
    private int age;
    private String email;

    // будуємо getter i setter i toString (+constructor)-> (ліва)Alt+Insert чи викор. Lombok + 2 анотації!

    @ElementCollection   // Додаємо колекціє skills до user
    private List<String> skills = new ArrayList<>();
    @Enumerated(EnumType.STRING)   // STRING - покаже назву поля; ORDINAL - 0 i 1!
    private Gender gender;

    // побудуємо конструктор Alt+Enter без ідентифікатора - він в нас вде є!
    public User(String name, int age, String email, List<String> skills, Gender gender) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.skills = skills;
        this.gender = gender;
    }
}










