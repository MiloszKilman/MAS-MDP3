import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

//overlapping przez spłaszczenie struktury
enum UserType {Standard, Contributor, LocalAdmin, GlobalAdmin};

//Wieloaspektowe
enum UserGender{Kobieta, Mężczyzna, inny};

public  class User  {

    //prosty
    private String name;
    //prosty
    private String lastName;
    private LocalDate hireDate;
    private int phoneNumber;
    //złożony
    private Addresses addresses;
    //klasowy
    private static int userCounter;
    //powtatrzalny
    private List<String> departments = new ArrayList<>();

    //overlapping
    private Set<UserType>  userType = Set.of(UserType.Standard);

    //drugiAspekt
    public UserGender userGender = UserGender.inny;


    //asocjacja zwykła
    public List<Group> groups;

    private static DatabaseManager databaseManager = new DatabaseManager();


    public User(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String [] department) {
        this.name = name;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        for (String ch : department) {
            if (!departments.contains(ch)) {
                departments.add(ch);
            }
        }
        userCounter++;
        databaseManager.saveUser(this);
    }

    public User(String name, String lastName, LocalDate hireDate, int phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
    }

    public static int getUserCounter() {
        return userCounter;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
    //przesłonięta metoda
    public String getHireDate(DateTimeFormatter formatter){
        String parsed = hireDate.format(formatter);
        return parsed;
    }
    //wyliczalny
    public int getTimeOfEmployment() {
        return Period.between (getHireDate(), LocalDate.now()).getYears();
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    // POKAZANIE ATRYBUTU POWTARZALNEGO
    public void showDepartmets() {
        System.out.println("Działy, w których zatrudniony jest: " + getName() + " " + getLastName() );
        for (String ch : departments) {
            System.out.println(ch);
        }
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(Group group) {
        this.groups.add(group);
    }

    //overlapping
    public Set<UserType> getUserType() {
        return userType;
    }

    public void setUserType(Set<UserType> userType) {
        this.userType = userType;
    }

    //drugi aspekt

    public UserGender getUserGender() {
        return userGender;
    }

    public void setUserGender(UserGender userGender) {
        this.userGender = userGender;
    }
    public void goMaternityLeave(){
        if(userGender.equals(userGender.Kobieta)) {
            System.out.println("Idź na urlop!");
        }
        else
            System.out.println("Niestety nie jesteś kobietą");
    }
    public void declareWar(){
        if(userGender.equals(userGender.Mężczyzna)) {
            System.out.println("Idź na wojne!");
        }
        else
            System.out.println("Zabieraj dzieci i wyjeżdzaj!");
    }

    //kompozycja
    private List<Mailbox> mailboxes = new ArrayList<>() ;
    public void addMailbox(String email){
        Mailbox mailbox = new Mailbox(email);
        mailboxes.add(mailbox);
    }

    public void removeMalbox(Mailbox mailbox){
        mailboxes.remove(mailbox);
    }

    public List<Mailbox> getMailboxes() {
        return mailboxes;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                ", phoneNumber=" + phoneNumber +
                ", addresses=" + addresses +
                ", departments=" + departments +
                '}';
    }

    //klasa wewnętrzna z konstruktorem prywatnym - kompozycja
    class Mailbox {
        private String numer;
        private String email;
        private  String name;

        private String DisplayName;
        private Mailbox(String email) {
            this.email = email;
            this.numer = generateRandomNumer();
        }

        private String generateRandomNumer() {
            Random random = new Random();
            char litera = (char) (random.nextInt(26) + 'A'); // Losowa wielka litera (A-Z)
            int cyfra = random.nextInt(10); // Losowa cyfra (0-9)
            return litera + String.valueOf(cyfra);
        }

        private String getNumer() {
            return numer;
        }

        private void setNumer(String numer) {
            this.numer = numer;
        }

        public String getEmail() {
            return email;
        }

        private void setEmail(String email) {
            this.email = email;
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private String getDisplayName() {
            return DisplayName;
        }

        private void setDisplayName(String displayName) {
            DisplayName = displayName;
        }
    }
}

