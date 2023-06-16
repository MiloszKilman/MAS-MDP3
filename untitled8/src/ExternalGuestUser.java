import java.time.LocalDate;

//wielodziedziczenie
public class ExternalGuestUser extends ExternalUser implements IGuestUser {
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
