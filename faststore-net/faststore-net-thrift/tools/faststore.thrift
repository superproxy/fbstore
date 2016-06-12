namespace java faststore.net.thrift.faststore.dataserver.server.api.faststore.framework.processor.request

typedef i32 int


struct  Request {
 1:  string  version;
 2:   i32   cmdCode;
 3:   string    body;
}

struct  Response {
1:  string  version;
2:  int   cmdCode;
3:   string    body;
}
service DataServerService {
	Response doHandler(1:Request  faststore.framework.processor.request),
	Response doHandler2(1:Request  faststore.framework.processor.request)
}
