package net.eleven.finance.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Created by eleven on 26.11.2018.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    @Positive
    private int quantity;
}
