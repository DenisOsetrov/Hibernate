package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarByOwner", referencedColumnName = "id")
    private List<Car> cars;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_driver", referencedColumnName = "id")
    public DriverLicense driverLicense;

    // Constructor
    public Owner(String name, List<Car> cars, DriverLicense driverLicense) {
        this.name = name;
        this.cars = cars;
        this.driverLicense = driverLicense;
    }
}
