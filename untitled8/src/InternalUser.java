import java.io.Serializable;
import java.time.LocalDate;

public class InternalUser extends User implements Serializable {
    private User manager;

    public InternalUser(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String[] department, User manager) {
        super(name, lastName, hireDate, phoneNumber, addresses, department);
        this.manager = manager;

    }

    public User getManager() {
        return manager;
    }

    public void setMenager(User menager) {
        this.manager = menager;
    }
}
