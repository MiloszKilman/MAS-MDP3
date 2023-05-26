import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {

    private String name;
    private String lastName;
    private LocalDate hireDate;
    private int phoneNumber;
    //złożony
    private Addresses addresses;
    //klasowy
    private static int userCounter;
    //powtatrzalny
    private List<String> departments = new ArrayList<>();

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


    //metoda klasowa
    public static int getUserCounter() {
        return userCounter;
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
    private static List<User> extent = new ArrayList<>();

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
