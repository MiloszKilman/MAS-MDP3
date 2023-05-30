import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//overlapping
enum UserType {Standard, Contributor, LocalAdmin, GlobalAdmin};

//płeć
enum UserGender{Kobieta, Mężczyzna, inny};

public  class User implements Serializable {

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
        extent.add(this);
    }

    public static int getUserCounter() {
        return userCounter;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
    //przesłonięta metod
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
    //metoda klasowa
    public static User getLongestWorkingUser() {
        User longestWorkingUser = extent.get(0);
        for (User user : extent) {
            if (user.getTimeOfEmployment() > longestWorkingUser.getTimeOfEmployment()) {
                longestWorkingUser = user;
            }
        }
        return longestWorkingUser;
    }


    //Esktecja klasy
    protected static List<User> extent = new ArrayList<>();

    private static void addUser(User user) {
        extent.add(user);
    }

    private static void removeUser(User user) {
        extent.remove(user);
    }

    public static void showExtent() {
        System.out.println("Extent of the class: " + User.class.getName());
        for (User user : extent) {
            System.out.println(user);
        }
    }

    //trwałość ekstecji
    public static void saveExtent(String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(extent);
            oos.close();
        } catch (IOException e) {
            System.err.println("Error while saving extent to file: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static void loadExtent(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            extent = (List<User>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while loading extent from file: " + e.getMessage());
        }
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
    public void idzNaUrlopMacierzyński(){
        if(userGender.equals(userGender.Kobieta)) {
            System.out.println("Idź na urlop!");
        }
        else
            System.out.println("Niestety nie jesteś kobietą");
    }
    public void wprowadźWojnę(){
        if(userGender.equals(userGender.Mężczyzna)) {
            System.out.println("Idź na wojne!");
        }
        else
            System.out.println("Zabieraj dzieci i wyjeżdzaj!");
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

}
