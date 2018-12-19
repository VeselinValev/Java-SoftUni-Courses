package app.models.dtos.views.wrappers;

import app.models.dtos.views.UserNamesAndProducts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNamesAndProductsWrapper {

    @XmlElement(name = "user")
    private List<UserNamesAndProducts> users;

    public UserNamesAndProductsWrapper() {
    }

    public List<UserNamesAndProducts> getUsers() {
        return users;
    }

    public void setUsers(List<UserNamesAndProducts> users) {
        this.users = users;
    }
}
