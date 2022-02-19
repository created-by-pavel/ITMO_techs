package models;

import tools.BanksException;

public class Client {
    private String _name;
    private String _surname;
    private String _address;
    private String _passportId;
    private Boolean _subscription = false;

    public Client(String name, String surname, String address, String passportId) throws BanksException {

        if (name == null || surname == null) throw new BanksException("write name and surname");
        _name = name;
        _surname = surname;
        _address = address;
        _passportId = passportId;
    }

    public Client() { }

    public static ClientBuilder createBuilder()
    {
        return new ClientBuilder();
    }

    public void subscription() { _subscription = true; }
    public boolean getSubscription() { return _subscription; }

    public void debitPercentsUpdated() { System.out.println("debit percents updated"); }

    public void creditLimitUpdated()  { System.out.println("credit percents updated"); }

    public void trustFactorLimitUpdated() { System.out.println("trustFactorLimit updated"); }

    public void depositPercentsUpdated() { System.out.println("deposit percents updated"); }

    public boolean trustFactor() { return _address != null || _passportId != null; }
    public String getName() {return _name;}
    public String getSurname() {return _surname;}

    public void setName(String name) { _name = name; }
    public void setSurname(String surname) { _surname = surname; }
    public void setAddress(String address) { _address = address; }

    public void setPassportId(String passportId) throws BanksException {

        if (passportId.toString().length() != 6) throw new BanksException("incorrect passportId");
        _passportId = passportId;
    }
}
