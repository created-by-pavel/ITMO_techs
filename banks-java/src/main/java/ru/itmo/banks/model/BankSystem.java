package ru.itmo.banks.model;

import ru.itmo.banks.tool.BanksException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    private final List<Bank> _banks;
    private final Time _time;

    public BankSystem(Time time) {
        _banks = new ArrayList<>();
        _time = time;
        _time.setBankSystem(this);
    }

    public void addBank(Bank bank)
    {
        _banks.add(bank);
    }

    public List<Bank> getBanks() { return _banks; }

    public IAccount addAccountToBank(Client client, Bank bank, AccountType accountType) {

        return bank.addAccountToBank(client, accountType, _time);
    }

    public void transfer(
            Client client,
            Bank bankFrom,
            IAccount accountFrom,
            IAccount accountTo,
            Bank bankTo,
            BigDecimal money) {

        if (!_banks.contains(bankFrom) || !_banks.contains(bankTo))
            throw new BanksException("cant find bank");

        if (!bankFrom.getClientAccounts().containsKey(client))
            throw new BanksException("cant find client");

        if (!bankFrom.getClientAccounts().get(client).contains(accountFrom))
            throw new BanksException("cant find account");

        if (!client.trustFactor() && money.compareTo(bankFrom.getTrustFactorLimit()) > 0)
            throw new BanksException("sum > trustFactorLimit");

        if(bankTo.getClientAccounts().values().
                stream().
                noneMatch(list -> list.contains(accountTo)))
            throw new BanksException("cant find accountTo");

        bankFrom.setCommand(new TransferCommand(accountFrom, accountTo, money));
        bankFrom.runCommand();
    }

    public void topUp(Client client, Bank bank, IAccount account, BigDecimal money) {
        if (!_banks.contains(bank))
            throw new BanksException("cant find bank");

        if (!bank.getClientAccounts().containsKey(client))
            throw new BanksException("cant client");

        if (!bank.getClientAccounts().get(client).contains(account))
            throw new BanksException("cant find account");

        bank.setCommand(new TopUpCommand(account, money));
        bank.runCommand();
    }

    public void withDraw(Client client, Bank bank, IAccount account, BigDecimal money) {
        if (!_banks.contains(bank))
            throw new BanksException("cant find bank");

        if (!bank.getClientAccounts().containsKey(client))
            throw new BanksException("cant find client");

        if (!bank.getClientAccounts().get(client).contains(account))
            throw new BanksException("cant find account");

        if (!client.trustFactor() && money.compareTo(bank.getTrustFactorLimit()) > 0)
            throw new BanksException("sum > trustFactorLimit");

        bank.setCommand(new WithDrawCommand(account, money));
        bank.runCommand();
    }

    public void cancelOperation(IAccount account, Bank bank, ICommand command) {
        if (!_banks.contains(bank))
            throw new BanksException("cant find bank");

        if(bank.getClientAccounts().values().
                stream().
                noneMatch(list -> list.contains(account)))
            throw new BanksException("cant find account");

        bank.cancelCommand(command);
    }

    public void notifyBank() {
        for(var bank : _banks) {
            for(var accounts : bank.getClientAccounts().values()) {
                for(var account : accounts) {

                    if(_time.getTime().getMonthValue() - account.getTimeStart().getMonthValue() >= 1 &&
                            _time.getTime().getDayOfMonth() - account.getTimeStart().getDayOfMonth() == 0) {
                        bank.notify(account);
                    }
                    else {
                        bank.topUpPercents(account);
                    }
                }
            }
        }
    }
}
