package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor

@Entity
public class DriverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String series;

    // Constructor
    public DriverLicense(int id, String series) {
        this.id = id;
        this.series = series;
    }
}
