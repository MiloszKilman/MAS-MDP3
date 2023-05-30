import java.io.Serializable;
import java.time.LocalDate;

public class ExternalUser extends User implements Serializable {
    private LocalDate payrollDayInMonth;

    public ExternalUser(String name, String lastName, LocalDate hireDate, int phoneNumber, Addresses addresses, String[] department, LocalDate payrollDayInMonth) {
        super(name, lastName, hireDate, phoneNumber, addresses, department);
        this.payrollDayInMonth = payrollDayInMonth;
    }

    public LocalDate getPayrollDayInMonth() {
        return payrollDayInMonth;
    }

    public void setPayrollDayInMonth(LocalDate payrollDayInMonth) {
        this.payrollDayInMonth = payrollDayInMonth;
    }
}
