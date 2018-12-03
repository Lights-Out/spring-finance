package net.eleven.finance.model;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eleven on 02.12.2018.
 */
@Entity
@Immutable
@AllArgsConstructor
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
    private Currency currency;

    private float rate;

    private Date forDate;

    @Enumerated(EnumType.ORDINAL)
    private RateProvider provider;
}
