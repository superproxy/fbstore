package faststore.configserver.api.group;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 14120295 on 2016/5/31.
 */
public class Group implements Serializable {
    String name;
    Date createdTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Group{" +
                "createdTime=" + createdTime +
                ", name='" + name + '\'' +
                '}';
    }
}
