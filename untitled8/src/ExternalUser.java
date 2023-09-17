import java.io.Serializable;
import java.time.LocalDate;

public class ExternalUser extends User implements Serializable {
    private LocalDate payrollDayInMonth;
    private int id;
    private static DatabaseManager databaseManager = new DatabaseManager();

    public ExternalUser(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String[] department, LocalDate payrollDayInMonth) {
        super(name, lastName, hireDate, phoneNumber, addresses, department);
        this.payrollDayInMonth = payrollDayInMonth;
        this.id = 0;

    }

    public LocalDate getPayrollDayInMonth() {
        return payrollDayInMonth;
    }

    public void setPayrollDayInMonth(LocalDate payrollDayInMonth) {
        this.payrollDayInMonth = payrollDayInMonth;
    }

    public void setId(int id) {
        this.id = id;
    }
}
