import java.time.LocalDate;

public class GuestUser extends User implements  IGuestUser{
    private String domainName;

    public GuestUser(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String[] department, String domainName) {
        super(name, lastName, hireDate, phoneNumber, addresses, department);
        this.domainName = domainName;
    }

    @Override
    public String getDomainName() {
        return this.domainName;

    }
}
