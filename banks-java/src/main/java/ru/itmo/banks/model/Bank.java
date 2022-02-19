package ru.itmo.banks.model;

import ru.itmo.banks.tool.BanksException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class Bank {
    private final Map<Client, List<IAccount>> _clientAccounts = new HashMap<>();
    private final List<ICommand> _commands = new ArrayList<>();
    private BigDecimal _commission;
    private LocalDate _depositTerm;
    private String _name;
    private BigDecimal _trustFactorLimit;
    private BigDecimal _debitPercent;
    private BigDecimal _creditLimit;
    private Map<BigDecimal, BigDecimal> _depositPercents;                      // <money, percent>
    private ICommand _command;

    public Bank(
            String name,
            BigDecimal trustFactorLimit,
            BigDecimal debitPercent,
            BigDecimal creditLimit,
            BigDecimal creditCommission,
            Map<BigDecimal, BigDecimal> depositPercents,
            LocalDate depositTerm)
    {
        _name = name;
        _trustFactorLimit = trustFactorLimit;
        _debitPercent = debitPercent.
                divide(BigDecimal.valueOf(365), RoundingMode.HALF_UP);
        _creditLimit = creditLimit;
        _commission = creditCommission;
        _depositPercents = depositPercents;
        _depositTerm = depositTerm;
    }

    public Bank() { }

    public String getBankName() { return _name; }

    public IAccount addAccountToBank(Client client, AccountType accountType, Time time)
            throws BanksException {

        short accountNumCount = 16;
        var random = new Random();
        var accountNum = new StringBuilder();
        while (accountNum.length() < accountNumCount)
        {
            accountNum.append(random.nextInt(10));
        }

        if (!_clientAccounts.containsKey(client)) {
            _clientAccounts.put(client, new ArrayList<>());
        }

        switch (accountType) {
            case CREDIT -> {
                Credit newCredit = new Credit(accountNum.toString(), _creditLimit, _commission);
                _clientAccounts.get(client).add(newCredit);
                return newCredit;
            }
            case DEBIT -> {
                Debit newDebit = new Debit(accountNum.toString());
                _clientAccounts.get(client).add(newDebit);
                return newDebit;
            }
            case DEPOSIT -> {
                Deposit newDeposit = new Deposit(accountNum.toString(), _depositTerm, time);
                _clientAccounts.get(client).add(newDeposit);
                return newDeposit;
            }
            default -> throw new BanksException("cant define this account");
        }
    }

    public void setCommand(ICommand command) {
        _command = command;
        _commands.add(command);
    }

    public void runCommand() throws BanksException {
        _command.execute();
    }

    public void cancelCommand(ICommand command) {
        if (!_commands.contains(command))
            throw new BanksException("bank cant fount this operation");
        command.undo();
    }

    public void notify(IAccount account) {
        if(account instanceof Debit || account instanceof Deposit) {
            account.topUp(account.getPercentOrCommissionBalance());
            account.percentOrCommissionBalanceToZero();
            return;
        }

        if(account instanceof Credit) {
            account.topUp(account.getPercentOrCommissionBalance().negate());
            account.percentOrCommissionBalanceToZero();
        }
    }

    public void topUpPercents(IAccount account) {
        if(account instanceof Debit) {
            account.topUpPercentOrCommission(
                    account.getBalance().
                            multiply(_debitPercent).
                            divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
            return;
        }

        if(account instanceof Deposit) {
            account.topUpPercentOrCommission(
                    account.getBalance().
                    multiply(selectDepositPercent(account)).
                    divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        }
    }

    public void attach(Client client)
    {
        client.subscription();
    }

    public void changeDebitPercent(BigDecimal debitPercent) {
        _debitPercent = debitPercent;
        notifyDebitClient();
    }

    public void changeCreditLimit(BigDecimal creditLimit) {
        _creditLimit = creditLimit;
        notifyCreditClient();
    }

    public void ChangeTrustFactorLimit(BigDecimal newTrustFactorLimit) {
        _trustFactorLimit = newTrustFactorLimit;
        notifyBadTrustFactorClient();
    }

    public void changeDepositPercents(Map<BigDecimal, BigDecimal> depositPercents) {
        _depositPercents = depositPercents;
        notifyDepositClient();
    }

    public List<ICommand> getCommands() { return _commands.stream().toList(); }

    public BigDecimal getTrustFactorLimit() { return _trustFactorLimit; }

    public Map<Client, List<IAccount>> getClientAccounts() { return _clientAccounts; }

    private BigDecimal selectDepositPercent(IAccount account) {

        for(Map.Entry<BigDecimal, BigDecimal> kvp : _depositPercents.entrySet()) {
            if(account.getBalance().compareTo(kvp.getKey()) > 0) continue;
            return kvp.getValue().
                    divide(BigDecimal.valueOf(365), 1,RoundingMode.HALF_UP);
        }

        return _depositPercents.values().
                stream().
                toList().
                get(_depositPercents.values().size() - 1).
                divide(BigDecimal.valueOf(365), RoundingMode.HALF_DOWN);
    }

    private void notifyCreditClient() {
        for (Map.Entry<Client, List<IAccount>> kvp : _clientAccounts.entrySet()) {
            if(kvp.getKey().getSubscription() && kvp.getValue().
                    stream().
                    anyMatch(a -> a instanceof Credit)) {
                kvp.getKey().creditLimitUpdated();
            }
        }
    }

    private void notifyDebitClient() {
        for (Map.Entry<Client, List<IAccount>> kvp : _clientAccounts.entrySet()) {
            if(kvp.getKey().getSubscription() && kvp.getValue().
                    stream().
                    anyMatch(a -> a instanceof Debit)) {
                kvp.getKey().debitPercentsUpdated();
            }
        }
    }

    private void notifyDepositClient() {
        for (Map.Entry<Client, List<IAccount>> kvp : _clientAccounts.entrySet()) {
            if(kvp.getKey().getSubscription() && kvp.getValue().
                    stream().
                    anyMatch(a -> a instanceof Deposit)) {
                kvp.getKey().depositPercentsUpdated();
            }
        }
    }

    private void notifyBadTrustFactorClient() {
        for (Map.Entry<Client, List<IAccount>> kvp : _clientAccounts.entrySet()) {
            if(kvp.getKey().getSubscription() && !kvp.getKey().trustFactor()) {
                kvp.getKey().trustFactorLimitUpdated();
            }
        }
    }
}
