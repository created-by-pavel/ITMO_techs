package models;

import tools.BanksException;

import java.math.BigDecimal;

public class TransferCommand implements ICommand{
    private final IAccount _accountFrom;
    private final IAccount _accountTo;
    private final BigDecimal _money;

    public TransferCommand(IAccount accountFrom, IAccount accountTo, BigDecimal money) {

        _accountFrom = accountFrom;
        _accountTo = accountTo;
        _money = money;
    }

    public void execute() throws BanksException {

        _accountFrom.transfer(_accountTo, _money);
    }

    public void undo() throws BanksException {

        if (_accountFrom == null && _accountTo == null)
        {
            throw new BanksException("this command not exit");
        }
        _accountFrom.topUp(_money);
        _accountTo.withDraw(_money);
    }
}
