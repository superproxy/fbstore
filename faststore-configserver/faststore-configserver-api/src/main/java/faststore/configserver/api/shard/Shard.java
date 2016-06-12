package faststore.configserver.api.shard;

import faststore.configserver.api.group.Group;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 14120295 on 2016/5/31.
 */
public class Shard implements Serializable {
    Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

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
        return "Shard{" +
                "createdTime=" + createdTime +
                ", group=" + group +
                ", name='" + name + '\'' +
                '}';
    }
}
