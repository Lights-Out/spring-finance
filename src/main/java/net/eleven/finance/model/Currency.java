package net.eleven.finance.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Created by eleven on 27.10.2018.
 */
@Entity
@Data
@NoArgsConstructor
public class Currency {

    public Currency(@NotEmpty String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String currencyCode;

    @UpdateTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
