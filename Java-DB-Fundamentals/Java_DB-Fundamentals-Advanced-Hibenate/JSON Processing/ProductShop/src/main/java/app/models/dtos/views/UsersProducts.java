package app.models.dtos.views;

import java.util.List;


public class UsersProducts {
    private Integer usersCount;

    private List<UserNamesWithAgeAndProducts> users;

    public UsersProducts(List<UserNamesWithAgeAndProducts> users) {
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
