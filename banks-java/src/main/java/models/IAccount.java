package models;

import tools.BanksException;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IAccount {
    public String getAccountNumber();
    public void topUp(BigDecimal money) throws BanksException;
    public void withDraw(BigDecimal money) throws BanksException;
    public void transfer(IAccount accountTo, BigDecimal money) throws BanksException;
    public BigDecimal getBalance();
    public void topUpPercentOrCommission(BigDecimal money) throws BanksException;
    public void percentOrCommissionBalanceToZero();
    public LocalDate getTimeStart();
    public BigDecimal getPercentOrCommissionBalance();
}
