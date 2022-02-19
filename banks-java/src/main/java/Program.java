import tools.BanksException;
import models.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws BanksException {
        Scanner in = new Scanner(System.in);
        var time = new Time();
        var bs = new BankSystem(time);
        String name, surname, address, passportId, bankName, choice, accountNumber, accountType;
        BigDecimal trustFactorLimit, debitPercent, creditLimit, creditCommission, sum, percent, money;
        int years, months, days, count;
        IAccount account = null;
        Client client = new Client();
        var bank = new Bank();

        System.out.println("\u001B[31m" + "COMMANDS TO CREATE" + "\u001B[0m");
        System.out.println("click '1' to create client");
        System.out.println("click '2' to create Bank");
        System.out.println("click '3' to add Account to Bank");
        System.out.println("click '4' to make Operation");

        System.out.println("\u001B[31m" + "COMMANDS TO SKIP TIME" + "\u001B[0m");
        System.out.println("click '5' to skip day");
        System.out.println("click '6' to skip month");
        System.out.println("click '7' to skip year");

        System.out.println("\u001B[31m" + "COMMANDS TO EDIT" + "\u001B[0m");
        System.out.println("click '8'  to add passportId or address to client");
        System.out.println("click '9' to edit Percents or commission");

        System.out.println("\u001B[31m" + "COMMANDS TO GET" + "\u001B[0m");
        System.out.println("click '0' to get Balance");
        System.out.println("enter 'exit' to stop program");

        var clients = new ArrayList<Client>();
        while (true)
        {
            var a = in.nextLine();
            switch (a)
            {
                case "exit":
                    return;
                case "0":
                    System.out.println("enter account number");
                    accountNumber = in.nextLine();
                    for (var b : bs.getBanks())
                    {
                        for (var kvp : b.getClientAccounts().entrySet())
                        {
                            for (var ac : kvp.getValue())
                            {
                                if(ac.getAccountNumber().equals(accountNumber))
                                    System.out.println(ac.getBalance());
                            }
                        }
                    }
                    break;

                case "1":
                    System.out.println("enter name");
                    name = in.nextLine();
                    System.out.println("enter surname");
                    surname = in.nextLine();
                    clients.add(new ClientBuilder().setName(name).setSurname(surname).create());
                    break;

                case "2":
                    System.out.println("enter bank name");
                    bankName = in.nextLine();

                    System.out.println("enter trustFactorLimit");
                    trustFactorLimit = in.nextBigDecimal();

                    System.out.println("enter debitPercent");
                    debitPercent = in.nextBigDecimal();

                    System.out.println("enter creditLimit (negative number)");
                    creditLimit = in.nextBigDecimal();

                    System.out.println("enter creditCommission");
                    creditCommission = in.nextBigDecimal();

                    System.out.println("now u need enter depositPercents");
                    System.out.println("enter count of percents");
                    count = in.nextInt();
                    var depositPercents = new HashMap<BigDecimal, BigDecimal>();
                    for (int i = 0; i < count; i++)
                    {
                        System.out.println("enter sum and then enter percent");
                        sum = in.nextBigDecimal();
                        percent = in.nextBigDecimal();
                        depositPercents.put(sum, percent);
                    }

                    System.out.println("now u need create depositTerm");
                    System.out.println("enter count of years, > 1");
                    years = in.nextInt();

                    System.out.println("enter count of months, > 1");
                    months = in.nextInt();

                    System.out.println("enter count of days, > 1");
                    days = in.nextInt();

                    var depositTerm = LocalDate.of(years, months, days);
                    bs.addBank(new Bank(bankName, trustFactorLimit, debitPercent, creditLimit, creditCommission, depositPercents, depositTerm));
                    break;

                case "3":
                    System.out.println("what kind of account do u want to create\n DEBIT, CREDIT, DEPOSIT");
                    accountType = in.nextLine();

                    System.out.println("for what client do u want to create account, please enter name and suranme");
                    name = in.nextLine();
                    surname = in.nextLine();

                    System.out.println("enter bank");
                    bankName = in.nextLine();

                    for(var b : bs.getBanks())
                    {
                        if (b.getBankName().equals(bankName))
                        {
                            bank = b;
                        }
                    }

                    for(var c : clients)
                    {
                        if (c.getName().equals(name) && c.getSurname().equals(surname))
                        {
                            client = c;
                        }
                    }

                    account = bs.addAccountToBank(client, bank, AccountType.valueOf(accountType));
                    System.out.println(account.getAccountNumber());
                    break;

                case "4":
                    System.out.println("what operation do u want to make");
                    System.out.println("Transfer - 1, WithDraw - 2, TopUp - 3, 4 - CancelOperation");
                    choice = in.nextLine();
                    System.out.println("for what client do u want to make operation, please enter name and suranme");
                    name = in.nextLine();
                    surname = in.nextLine();

                    for (var c : clients)
                    {
                        if (c.getName().equals(name) && c.getSurname().equals(surname))
                        {
                            client = c;
                        }
                    }
                    System.out.println("enter bank");
                    bankName = in.nextLine();
                    String finalBankName1 = bankName;
                    bank = bs.getBanks().stream().filter(b -> b.getBankName().equals(finalBankName1)).findFirst().get(); // ?

                    System.out.println("on what account do u want to do this operation, enter account_number");
                    for (Bank b : bs.getBanks())
                    {
                        for(var ac : b.getClientAccounts().get(client))
                        {
                            System.out.println(ac.getAccountNumber());
                        }
                    }

                    accountNumber = in.nextLine();
                    String finalAccountNumber = accountNumber;
                    account = bank.getClientAccounts().get(client).stream().filter(ac -> ac.getAccountNumber().equals(finalAccountNumber)).findFirst().get();

                    switch (choice)
                    {
                        case "1":
                            System.out.println("on what account do u want to transfer, enter account_number");
                            IAccount account2 = null;
                            var bank2 = new Bank();
                            for (var b : bs.getBanks())
                            {
                                for (var kvp : b.getClientAccounts().entrySet())
                                {
                                    for (var ac : kvp.getValue())
                                    {
                                        System.out.println(ac.getAccountNumber());
                                    }
                                }
                            }

                            String accountNumber2 = in.nextLine();
                            for (var b : bs.getBanks())
                            {
                                for (var kvp : bank.getClientAccounts().entrySet())
                                {
                                    for (var ac : kvp.getValue())
                                    {
                                        if (!ac.getAccountNumber().equals(accountNumber2)) continue;
                                        bank2 = b;
                                        account2 = ac;
                                    }
                                }
                            }
                            System.out.println("enter sum");
                            money = in.nextBigDecimal();
                            bs.transfer(client, bank, account, account2, bank2, money);
                            break;

                        case "2":
                            System.out.println("enter sum");
                            money = in.nextBigDecimal();
                            bs.withDraw(client, bank, account, money);
                            break;
                        case "3":
                            System.out.println("enter sum");
                            money = in.nextBigDecimal();
                            bs.topUp(client, bank, account, money);
                            break;
                    }

                    break;
                case "5":
                    time.skipDay();
                    System.out.println(time.getTime());
                    break;
                case "6":
                    time.skipMonth();
                    System.out.println(time.getTime());
                    break;
                case "7":
                    time.skipYear();
                    System.out.println(time.getTime());
                    break;
                case "8":
                    System.out.println("for what client do you want to add passportID or address");
                    name = in.nextLine();
                    surname = in.nextLine();
                    String finalName = name;
                    String finalSurname = surname;
                    client = clients.stream().filter(c -> c.getName().equals(finalName) && c.getSurname().equals(finalSurname)).findFirst().get(); // ?

                    System.out.println("what do u want to add");
                    System.out.println("1 - address, 2 - passportId");
                    choice = in.nextLine();

                    switch (choice)
                    {
                        case "1":
                            address = in.nextLine();
                            client.setAddress(address);
                            break;
                        case "2":
                            passportId = in.nextLine();
                            client.setPassportId(passportId);
                            break;
                    }

                    break;
                case "9":
                    System.out.println("what do u want to edit");
                    System.out.println("1 - debitPercent, 2 - creditLimit, 3 - depositPercent, 4 - trustFactorLimit");
                    choice = in.nextLine();
                    System.out.println("for what bank do u want to edit");
                    System.out.println("enter bankName");
                    bankName = in.nextLine();
                    String finalBankName = bankName;
                    bank = bs.getBanks().stream().filter(b -> b.getBankName().equals(finalBankName)).findFirst().get(); // ?

                    switch (choice)
                    {
                        case "1":
                            System.out.println("enter debitPercent");
                            debitPercent = in.nextBigDecimal();
                            bank.changeDebitPercent(debitPercent);
                            break;
                        case "2":
                            System.out.println("enter creditLimit");
                            creditLimit = in.nextBigDecimal();
                            bank.changeCreditLimit(creditLimit);
                            break;
                        case "3":
                            System.out.println("enter count of percents");
                            count = in.nextInt();
                            var newDepositPercents = new HashMap<BigDecimal, BigDecimal>();
                            for (int i = 0; i < count; i++)
                            {
                                System.out.println("enter sum and then enter percent");
                                sum = in.nextBigDecimal();
                                percent = in.nextBigDecimal();
                                newDepositPercents.put(sum, percent);
                                bank.changeDepositPercents(newDepositPercents);
                            }

                            break;
                        case "4":
                            System.out.println("enter trustFactorLimit");
                            trustFactorLimit = in.nextBigDecimal();
                            bank.changeCreditLimit(trustFactorLimit);
                            break;
                    }

                    break;
            }
        }
    }
}
