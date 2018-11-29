package net.eleven.finance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotEmpty
    private String name;

    @PositiveOrZero
    private double price;

    @NotNull
    private Currency currency;
}
