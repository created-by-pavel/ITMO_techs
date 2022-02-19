package tools;

public class BanksException extends Exception{

    public BanksException(){ }

    public BanksException(String message)  { super(message); }

    public BanksException(String message, Exception exception) { super(message, exception); }
}
