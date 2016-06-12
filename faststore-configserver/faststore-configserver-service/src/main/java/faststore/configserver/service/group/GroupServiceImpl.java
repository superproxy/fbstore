package faststore.configserver.service.group;

import faststore.configserver.api.common.Path;
import faststore.configserver.api.group.Group;
import faststore.configserver.api.group.GroupService;
import faststore.configserver.service.common.PathCallBack;
import faststore.configserver.service.zk.ClientFactory;
import faststore.configserver.service.zk.ZkService;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private ZkService zkService = ClientFactory.getZkService();

    @Override
    public List<Group> find(Object... objects) throws Exception {
        return zkService.find(Path.GROUPS, new PathCallBack() {
            @Override
            public String getPath(String path) {
                return Path.buildGroupInfo(path);
            }
        }, Group.class);
    }

    @Override
    public void add(Group group) throws Exception {
        zkService.add(Path.buildGroupInfo(group.getName()), group);
    }

    @Override
    public void delete(Group group) throws Exception {
        zkService.delete(Path.buildGroup(group.getName()));
    }
}
