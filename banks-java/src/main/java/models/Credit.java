package models;

import tools.BanksException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Credit implements IAccount{
    private final LocalDate _timeStart;
    private final BigDecimal _creditLimit;
    private final String _id;
    private final String _accountNum;
    private BigDecimal _commission;
    private BigDecimal _balance = BigDecimal.ZERO;
    private BigDecimal _commissionBalance = BigDecimal.ZERO;

    public Credit(String accountNum, BigDecimal creditLimit, BigDecimal commission) {

        _timeStart = LocalDate.now();
        _accountNum = accountNum;
        _creditLimit = creditLimit;
        _commission = commission;
        _id = UUID.randomUUID().toString();
    }

    public String getAccountNumber() { return _accountNum; }

    public void topUp(BigDecimal money) { _balance = _balance.add(money); }

    public void transfer(IAccount accountTo, BigDecimal money) throws BanksException {

        if (_balance.signum() == -1)
        {
            if (_balance.subtract(money).subtract(_commission).compareTo(_creditLimit) >= -1) {
                throw new BanksException("insufficient funds");
            }
            _balance = _balance.subtract(money);
            accountTo.topUp(money);
            topUpPercentOrCommission(_commission);
            return;
        }

        _balance = _balance.subtract(money);
        accountTo.topUp(money);
    }

    public void withDraw(BigDecimal money) throws BanksException {

        if (_balance.compareTo(BigDecimal.ZERO) <= 0)
        {
            if (_balance.subtract(money).subtract(_commission).compareTo(_creditLimit) < 0)
                throw new BanksException("insufficient funds");

            _balance = _balance.subtract(money);
            topUpPercentOrCommission(_commission);
            return;
        }

        _balance = _balance.subtract(money);
    }

    public void topUpPercentOrCommission(BigDecimal money)
    {
        _commissionBalance = _commissionBalance.add(money);
    }

    public void percentOrCommissionBalanceToZero()
    {
        _commission = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() { return _balance; }

    public BigDecimal getPercentOrCommissionBalance() { return _commissionBalance; }

    public LocalDate getTimeStart() { return _timeStart; }

    public void minusSum(BigDecimal money) { _balance = _balance.add(money); }
}
