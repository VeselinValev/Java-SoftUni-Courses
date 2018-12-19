package app.models.dtos.views.wrappers;

import app.models.dtos.views.UserNamesWithAgeAndProducts;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersProductsWrapper {

    @XmlAttribute(name = "count")
    private Integer usersCount;

    @XmlElement(name = "user")
    private List<UserNamesWithAgeAndProducts> users;

    public UsersProductsWrapper() {
    }

    public UsersProductsWrapper(List<UserNamesWithAgeAndProducts> users) {
        this.users = users;
        this.usersCount = users.size();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserNamesWithAgeAndProducts> getUsers() {
        return users;
    }

    public void setUsers(List<UserNamesWithAgeAndProducts> users) {
        this.users = users;
    }
}
