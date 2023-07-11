package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class DriverLicense {
    @Id
    private String id;

    private String series;

    public DriverLicense(String id, String series) {
        this.id = id;
        this.series = series;
    }
}