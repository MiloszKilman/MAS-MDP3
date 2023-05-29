import java.time.LocalDate;
import java.util.List;

//wielodziedziczenie
public class ExternalGuestUser extends ExternalUser implements GuestUser {
    private String domainName;
    public ExternalGuestUser(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String[] department, LocalDate payrollDayInMonth, String domainName) {
        super(name, lastName, hireDate, phoneNumber, addresses, department, payrollDayInMonth);
        this.domainName = domainName;
    }

    @Override
    public String getDomainName() {
        return this.domainName;
    }


}
