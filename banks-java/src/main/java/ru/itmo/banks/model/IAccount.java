package ru.itmo.banks.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IAccount {
     String getAccountNumber();
     void topUp(BigDecimal money);
     void withDraw(BigDecimal money);
     void transfer(IAccount accountTo, BigDecimal money);
     BigDecimal getBalance();
     void topUpPercentOrCommission(BigDecimal money);
     void percentOrCommissionBalanceToZero();
     LocalDate getTimeStart();
     BigDecimal getPercentOrCommissionBalance();
}
