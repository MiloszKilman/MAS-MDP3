import java.util.ArrayList;
import java.util.List;

public class Group {
    protected String Name;
    private List<User> members;

    public Group(String name) {
        Name = name;
        members=new ArrayList<>();
    }
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
    public void setMember(User user){
        members.add(user);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
