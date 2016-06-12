package faststore.configserver.api.leader;

public interface LeaderService {
    void start() throws Exception;
    void close() throws Exception;
}
