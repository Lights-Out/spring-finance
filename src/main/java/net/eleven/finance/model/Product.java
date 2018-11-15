package net.eleven.finance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Created by eleven on 27.10.2018.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    public Product(@NotEmpty String name, @Positive double price, @NotEmpty Currency currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @Positive
    private double price;

    @NotEmpty
    private Currency currency;

    @UpdateTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
