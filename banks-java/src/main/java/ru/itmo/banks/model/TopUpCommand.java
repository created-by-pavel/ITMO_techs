package ru.itmo.banks.model;

import ru.itmo.banks.tool.BanksException;

import java.math.BigDecimal;

public class TopUpCommand implements ICommand {
    private final IAccount _receiver;
    private final BigDecimal _money;

    public TopUpCommand(IAccount receiver, BigDecimal money) {
        _receiver = receiver;
        _money = money;
    }

    public void execute() {
        _receiver.topUp(_money);
    }

    public void undo() {
        if (_receiver == null)
            throw new BanksException("this command not exit");

        _receiver.withDraw(_money);
    }
}
