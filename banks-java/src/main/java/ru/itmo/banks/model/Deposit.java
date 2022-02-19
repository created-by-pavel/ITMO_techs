package ru.itmo.banks.model;

import ru.itmo.banks.tool.BanksException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Deposit implements IAccount {
    private final LocalDate _timeStart;
    private final LocalDate _depositTerm;
    private final Time _time;
    private final String _accountNum;
    private BigDecimal _balance = BigDecimal.ZERO;
    private BigDecimal _percentBalance = BigDecimal.ZERO;
    private String _id;

    public Deposit(String accountNum, LocalDate depositTerm, Time time) {
        _timeStart = LocalDate.now();
        _accountNum = accountNum;
        _depositTerm = _timeStart
                .plusDays(depositTerm.getDayOfMonth())
                .plusMonths(depositTerm.getMonthValue())
                .plusYears(depositTerm.getYear());
        _time = time;
        _id = UUID.randomUUID().toString();
    }

    public String getAccountNumber() { return _accountNum; }

    public void topUp(BigDecimal money) { _balance = _balance.add(money); }

    public void transfer(IAccount accountTo, BigDecimal money) {
        if (_time.getTime().compareTo(_depositTerm) < 0)
            throw new BanksException("you can't do it yet");

        if (_balance.compareTo(money) < 0)
            throw new BanksException("insufficient funds");

        _balance = _balance.subtract(money);
        accountTo.topUp(money);
    }

    public void withDraw(BigDecimal money) {
        if (_time.getTime().compareTo(_depositTerm) < 0)
            throw new BanksException("you can't do it yet");

        if (_balance.compareTo(money) < 0)
            throw new BanksException("insufficient funds");

        _balance = _balance.subtract(money);
    }

    public void topUpPercentOrCommission(BigDecimal money)
    {
        _percentBalance = _percentBalance.add(money);
    }

    public void percentOrCommissionBalanceToZero()
    {
        _percentBalance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() { return _balance; }
    public BigDecimal getPercentOrCommissionBalance() { return _percentBalance; }
    public LocalDate getTimeStart() { return _timeStart; }
}
