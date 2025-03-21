package base.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Order order;

    @NotBlank
    @Size(min = 4, message = "Payment method invalid")
    private String paymentMethod;

    //payment gateway
    private String pgPaymentId;
    private String pgName;
    private String pgStatus;
    private String pgResponseMessage;

    public Payment(String paymentMethod, String pgPaymentId, String pgName, String pgStatus, String pgResponseMessage) {
        this.paymentMethod = paymentMethod;
        this.pgPaymentId = pgPaymentId;
        this.pgName = pgName;
        this.pgStatus = pgStatus;
        this.pgResponseMessage = pgResponseMessage;
    }
}
