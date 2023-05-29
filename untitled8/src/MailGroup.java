public class MailGroup extends Group {
    private String emailAddress;

    public MailGroup(Group group, String emailAddress) {
        super(group.Name);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
