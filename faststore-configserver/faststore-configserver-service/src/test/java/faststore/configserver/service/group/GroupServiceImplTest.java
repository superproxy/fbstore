package faststore.configserver.service.group;


import faststore.configserver.api.group.Group;
import faststore.configserver.api.group.GroupService;
import faststore.configserver.api.shard.Shard;
import faststore.configserver.api.shard.ShardService;
import faststore.configserver.service.shard.ShardServiceImpl;
import org.testng.annotations.Test;

import java.util.List;

public class GroupServiceImplTest {


    GroupService groupService = new GroupServiceImpl();

    ShardService shardService = new ShardServiceImpl();

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        Group group = new Group();
        group.setName("group1");
        groupService.add(group);
        group = new Group();
        group.setName("group2");

        groupService.add(group);
        List<Group> groups = groupService.find();
        for (Group g : groups) {
            System.out.println(g);
        }

        Shard shard = new Shard();
        shard.setGroup(group);

        shardService.add(shard);
        List<Shard> shardList = shardService.find(group.getName());
        for (Shard s : shardList) {
            System.out.println(s);
        }


    }

    @Test
    public void testDelete() throws Exception {
        Group group = new Group();
        group.setName("group1");
        groupService.delete(group);
    }
}