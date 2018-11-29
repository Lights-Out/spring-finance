package net.eleven.finance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * Created by eleven on 27.10.2018.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    public Product(@NotEmpty String name, @PositiveOrZero double price, @NotNull Currency currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double price;

    @ManyToOne
    private Currency currency;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
}

