package models;

import tools.BanksException;

public interface ICommand {
    void execute() throws BanksException;
    void undo() throws BanksException;
}
