package models;

import tools.BanksException;

import java.math.BigDecimal;

public class WithDrawCommand implements ICommand{
    private final IAccount _receiver;
    private final BigDecimal _money;

    public WithDrawCommand(IAccount receiver, BigDecimal money) {

        _receiver = receiver;
        _money = money;
    }

    public void execute() throws BanksException {

        _receiver.withDraw(_money);
    }

    public void undo() throws BanksException {

        if (_receiver == null) {
            throw new BanksException("this command not exit");
        }
        _receiver.topUp(_money);
    }
}
