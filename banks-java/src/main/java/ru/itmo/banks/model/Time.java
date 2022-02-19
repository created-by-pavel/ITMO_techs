package ru.itmo.banks.model;

import java.time.LocalDate;

public class Time {
    private BankSystem _bankSystem;
    private LocalDate _dateTime;

    public Time() {
        _dateTime = LocalDate.now();
    }

    public void skipDay() {
        _dateTime = _dateTime.plusDays(1);
        _bankSystem.notifyBank();
    }

    public void skipMonth() {
        LocalDate tmp = _dateTime.plusMonths(1);
        while (_dateTime.getDayOfMonth()
                != tmp.getDayOfMonth()
                || _dateTime.getMonth()
                != tmp.getMonth())
        {
            skipDay();
        }
    }

    public void skipYear() {
        LocalDate tmp = _dateTime.plusYears(1);
        while (_dateTime.getDayOfMonth() != tmp.getDayOfMonth()
                || _dateTime.getMonth() != tmp.getMonth()
                || _dateTime.getYear() != tmp.getYear())
        {
            skipDay();
        }
    }

    public LocalDate getTime() { return _dateTime; }
    public void setBankSystem(BankSystem bankSystem) { _bankSystem = bankSystem; }
}
