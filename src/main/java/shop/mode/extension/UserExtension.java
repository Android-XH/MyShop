package shop.mode.extension;

import shop.annotation.Group;
import shop.annotation.ORMAnnotation.Enumerated;
import shop.mode.base.BasePOJO;

public class UserExtension extends BasePOJO {
    private String token;

    @Enumerated(var = "group_")
    private Group group;

    public Group getGroup(){
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}