package ru.itmo.banks.model;

public interface ICommand {
    void execute();
    void undo();
}
