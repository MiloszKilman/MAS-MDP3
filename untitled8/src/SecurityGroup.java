public class SecurityGroup extends Group {
    private boolean FilePermission;


    public SecurityGroup(Group group, boolean FilePermission) {
        super(group.Name);
        this.FilePermission=FilePermission;
    }

    public boolean isFilePermission() {
        return FilePermission;
    }
}
