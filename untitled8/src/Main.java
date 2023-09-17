import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //GUI
        SwingUtilities.invokeLater(() -> new UserTableGUI());
        //adresy
        Addresses adres = new Addresses("Warszawa", "Koszykowa", 86, "00-014");
        Addresses adres2 = new Addresses("Miłomłyn", "Kwiatowa", 1,  "14-140");

        //daty
        LocalDate date = LocalDate.parse("2000-04-11");
        LocalDate date1 = LocalDate.parse("1998-06-01");
        LocalDate date2 = LocalDate.parse("2018-03-29");
        LocalDate date3 = LocalDate.parse("2023-07-17");
        LocalDate date4 = LocalDate.parse("2024-03-23");


        //Polimorfizm
        User user1 = new ExternalUser("Jan", "Kowalski",
                date, 50506708, adres , new String[] {"IT"}, date3);
        User user2 = new InternalUser("Anna", "Nowak",
                date1, 50506709, adres2, new String[] {"IT", "Development"}, user1 );
        System.out.println("User 1, pozostaje zatrudniony " + user1.getTimeOfEmployment() + " lata.");
        User user3 = new ExternalUser("Michał", "Nowakowski",
                date2, 606034258, adres2, new String[] {"IT", "Administrator"}, date4 );
        //polimorfizm
        User user4 = new InternalUser("Miłosz", "Kilman",
                date2, 606034258, adres2, new String[] {"IT", "Administrator"}, user3 );
        //wielodziedziczenie
        User user5 = new ExternalGuestUser("John", "Rambo",
                date2, 606034258, adres2, new String[] {"Controller"}, date4, "externaldomain.com" );


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


        //dziedziczenie
        System.out.println("Przełożony: "+user4.getName()+ " "+ user4.getLastName()+":");
        System.out.println(((InternalUser) user4).getManager());
        System.out.println("Data płatnościc dla " + user3.getName()+":");
        System.out.println(((ExternalUser) user3).getPayrollDayInMonth());


        //dynamiczne
        Group readInPublic = new Group("readInPublic");
        Group readPublic = new SecurityGroup(readInPublic, true);
        readPublic.setMember(user1);
        readPublic.setMember(user2);
        readPublic.setMember(user3);
        System.out.println(readInPublic.getMembers());
        System.out.println();
        Group marketing = new MailGroup(readInPublic, "marketing");
        System.out.println("Adres grupy: "+readPublic.getName());
        System.out.println(((MailGroup) marketing).getEmailAddress());
        System.out.println();

        user1.addMailbox("user1@test.pl");
        user2.addMailbox("user2@test.pl");
        System.out.println("Adres mailowy " + user1.getName()+":");
        List<User.Mailbox> userMailboxes = user1.getMailboxes();
        for (User.Mailbox mailbox : userMailboxes) {
            System.out.println(" - " + mailbox.getEmail());
        }
    }
}
