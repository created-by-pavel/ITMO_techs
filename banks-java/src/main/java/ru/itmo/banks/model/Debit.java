package ru.itmo.banks.model;

import ru.itmo.banks.tool.BanksException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Debit implements IAccount {
    private final LocalDate _timeStart;
    private BigDecimal _balance = BigDecimal.ZERO;
    private final String _accountNum;
    private String _id;
    private BigDecimal _percentBalance = BigDecimal.ZERO;

    public Debit(String accountNum) {
        _timeStart = LocalDate.now();
        _accountNum = accountNum;
        _id = UUID.randomUUID().toString();
    }

    public String getAccountNumber() { return _accountNum; };

    public void transfer(IAccount accountTo, BigDecimal money) {
        if (_balance.compareTo(money) < 0)
            throw new BanksException("insufficient funds");

        _balance = _balance.subtract(money);
        accountTo.topUp(money);
    }

    public void topUp(BigDecimal money) { _balance = _balance.add(money); }

    public void withDraw(BigDecimal money) {
        if (_balance.compareTo(money) < 0)
            throw new BanksException("insufficient funds");

        _balance = _balance.subtract(money);
    }

    public void topUpPercentOrCommission(BigDecimal money) {
        _percentBalance = _percentBalance.add(money);
    }

    public void percentOrCommissionBalanceToZero() {
        _percentBalance = BigDecimal.ZERO;
    }

    public BigDecimal getPercentOrCommissionBalance() { return _percentBalance; }

    public BigDecimal getBalance() { return _balance; }
    public LocalDate getTimeStart() { return _timeStart; }
}
