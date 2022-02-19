package models;

import tools.BanksException;

public class ClientBuilder {
    private String _name;
    private String _surname;
    private String _address;
    private String _passportId;

    public Client create() throws BanksException {

        return new Client(_name, _surname, _address, _passportId);
    }

    public ClientBuilder setName(String name) {

        _name = name;
        return this;
    }

    public ClientBuilder setSurname(String surname) {

        _surname = surname;
        return this;
    }

    public ClientBuilder setAddress(String address) {

        _address = address;
        return this;
    }

    public ClientBuilder setPassportId(String passportId) {

        _passportId = passportId;
        return this;
    }
}
