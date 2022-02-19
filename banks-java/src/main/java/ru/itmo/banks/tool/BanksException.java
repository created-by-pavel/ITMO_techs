package ru.itmo.banks.tool;

public class BanksException extends RuntimeException {

    public BanksException(){ }

    public BanksException(String message)  { super(message); }

    public BanksException(String message, Exception exception) { super(message, exception); }
}
