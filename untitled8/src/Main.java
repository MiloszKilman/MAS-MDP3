import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        Addresses adres = new Addresses("Warszawa", "Koszykowa", 86, "00-014");
        Addresses adres2 = new Addresses("Miłomłyn", "Kwiatowa", 1, 56, "14-140");
        LocalDate date = LocalDate.parse("2000-04-11");
        LocalDate date1 = LocalDate.parse("1998-06-01");
        LocalDate date2 = LocalDate.parse("2018-03-29");
        User user1 = new User("Jan", "Kowalski",
                date, 50506708, adres , new String[] {"IT"});
        User user2 = new User("Anna", "Nowak",
                date1, 50506709, adres2, new String[] {"IT", "Development"} );
        System.out.println("User 1, pozostaje zatrudniony " + user1.getTimeOfEmployment() + " lata.");
        User user3 = new User("Michał", "Nowakowski",
                date2, 606034258, adres2, new String[] {"IT", "Administrator"} );
        System.out.println("Format yyyy-mm-dd");
        System.out.println("User 1, pracuje od: " + user1.getHireDate());
        System.out.println("Format dd-mm-yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("User 1, pracuje od: " + user1.getHireDate(formatter));
        //klasowy
        System.out.println("Liczba zatrudnionych to: "+ User.getUserCounter());
        System.out.println();
        user1.showDepartmets();
        user2.showDepartmets();
        System.out.println();
        //metoda klasowa
        System.out.println("Najdłużej pracujący to: " + User.getLongestWorkingUser());
        System.out.println();
        //lokalizajcja ekstecji
        String fname = System.getProperty("user.home") + "/ekstencja.dat";
        User.saveExtent(fname);
        User.loadExtent(fname);
        User.showExtent();
    }
}
