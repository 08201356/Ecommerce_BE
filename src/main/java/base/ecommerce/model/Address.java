package base.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 characters long.")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at least 5 characters long.")
    private String buildingName;

    @NotBlank
    @Size(min = 5, message = "District name must be at least 5 characters long.")
    private String district;

    @NotBlank
    @Size(min = 3, message = "Province name must be at least 3 characters long.")
    private String province;

    @NotBlank
    @Size(min = 5, message = "Country name must be at least 5 characters long.")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Pin code must be at least 5 characters long.")
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String street, String buildingName, String district, String province, String country, String pinCode) {
        this.street = street;
        this.buildingName = buildingName;
        this.district = district;
        this.province = province;
        this.country = country;
        this.pinCode = pinCode;
    }
}
