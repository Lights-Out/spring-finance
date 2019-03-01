package net.eleven.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eleven on 02.12.2018.
 */
@Entity
@AllArgsConstructor
@Getter
public class CurrencyRate {
    public CurrencyRate(Currency currency, float rate, Date forDate, RateProvider provider) {
        this.currency = currency;
        this.rate = rate;
        this.forDate = forDate;
        this.provider = provider;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @Setter
    private Currency currency;

    private float rate;

    private Date forDate;

    @Enumerated(EnumType.ORDINAL)
    private RateProvider provider;
}
