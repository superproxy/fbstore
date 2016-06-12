package faststore.framework.processor.biz.handler;


public class DefaultRequestHandlerChains extends AbstractRequestHandlerChains {
    private static DefaultRequestHandlerChains instance = new DefaultRequestHandlerChains();

    private DefaultRequestHandlerChains() {
    }

    public static DefaultRequestHandlerChains getInstance() {
        return instance;
    }


}
