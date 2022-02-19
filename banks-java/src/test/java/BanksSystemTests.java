
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.BanksException;
import models.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThrows;

public class BanksSystemTests {
    private Time _time;
    private BankSystem _bs;
    private Bank _tinkov;
    private Bank _sberbank;
    private Client _pavel;
    private Client _kolya;
    private IAccount _kolyaDeposit;
    private IAccount _pavelDebit;

    @Before
    public void Setup() throws BanksException {
        _time = new Time();
        _bs = new BankSystem(_time);
        LocalDate _depositTerm = LocalDate.of(0001, 1, 01);
        Map<BigDecimal, BigDecimal> _depositPercents = new HashMap<>();
        _depositPercents.put(BigDecimal.valueOf(50_000), BigDecimal.valueOf(3));
        _depositPercents.put(BigDecimal.valueOf(100_000), BigDecimal.valueOf(3.5));
        _depositPercents.put(BigDecimal.valueOf(200_000), BigDecimal.valueOf(4));
        _pavel = new ClientBuilder().setName("pavel").setSurname("zavalnyuk").create();
        _kolya = new ClientBuilder().setName("kolya").setSurname("kondratyev").setPassportId("411777").setAddress("dymskaya 4").create();
        _tinkov = new Bank("tinkov", BigDecimal.valueOf(200_000), BigDecimal.valueOf(3.65), BigDecimal.valueOf(-20000), BigDecimal.valueOf(100), _depositPercents, _depositTerm);
        _sberbank = new Bank("sberbank", BigDecimal.valueOf(1000000), BigDecimal.valueOf(7.3), BigDecimal.valueOf(-10000), BigDecimal.valueOf(50), _depositPercents, _depositTerm);
        _bs.addBank(_sberbank);
        _bs.addBank(_tinkov);
        _kolyaDeposit = _bs.addAccountToBank(_kolya, _sberbank, AccountType.DEPOSIT);
        _pavelDebit = _bs.addAccountToBank(_pavel, _tinkov, AccountType.DEBIT);
        _bs.topUp(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(100_000));
        _bs.topUp(_kolya, _sberbank, _kolyaDeposit, BigDecimal.valueOf(40_000));
    }

    @Test
    public void Transfer() throws BanksException {
        _bs.transfer(_pavel, _tinkov, _pavelDebit, _kolyaDeposit, _sberbank, BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(41000), _kolyaDeposit.getBalance());
    }

    @Test
    public void TopUp() throws BanksException {
        _bs.topUp(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(101_000), _pavelDebit.getBalance());
    }

    @Test
    public void WithDraw() throws BanksException {
        _bs.withDraw(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(99_000), _pavelDebit.getBalance());
    }

    @Test
    public void SkipDay() throws BanksException {
        _time.skipDay();
        Assert.assertEquals("10.00", _pavelDebit.getPercentOrCommissionBalance().toString());
    }

    @Test
    public void CancelOperation() throws BanksException {
        _bs.topUp(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(10_000));
        _bs.topUp(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(1));
        _bs.cancelOperation(_pavelDebit, _tinkov, _tinkov.getCommands().get(0));
        Assert.assertEquals(BigDecimal.valueOf(10_001), _pavelDebit.getBalance());
    }

    @Test
    public void TimeIsNotUp_ForDeposit_ThrowException()
    {
        assertThrows(BanksException.class, ()->{
            _bs.withDraw(_kolya, _sberbank, _kolyaDeposit, BigDecimal.valueOf(10_000));
        });

    }

    @Test
    public void WithDrawMoney_IsBiggerThan_TrustFactorLimit_ThrowException() throws BanksException {
        _pavelDebit.topUp(BigDecimal.valueOf(200_000));

        assertThrows(BanksException.class, ()->{
            _bs.withDraw(_pavel, _tinkov, _pavelDebit, BigDecimal.valueOf(201_000));
        });
    }

    @Test
    public void WithDrawMoney_IsBiggerThan_CreditLimit_ThrowException() throws BanksException {
        var pavelCredit = _bs.addAccountToBank(_pavel, _tinkov, AccountType.CREDIT);

        assertThrows(BanksException.class, ()->{
            _bs.withDraw(_pavel, _tinkov, pavelCredit, BigDecimal.valueOf(30_000));
        });
    }
}
