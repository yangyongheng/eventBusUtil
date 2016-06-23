package me.andpay.mobile.eventbus;

import java.lang.reflect.Method;

/**
 * 消息分发工具
 */
public class AMDispatchTools {

    private static volatile AMDispatchTools defaultInstance = new AMDispatchTools();

    private static AMDispatchTools shareInstance() {
        if(defaultInstance == null) {
            synchronized(AMDispatchTools.class) {
                if(defaultInstance == null) {
                    defaultInstance = new AMDispatchTools();
                }
            }
        }
        return defaultInstance;
    }
    static {
        AMEventBusFactory.getDefaultEeventBus().register(AMDispatchTools.shareInstance());
    }

    /**
     * 把消息分发到主线程执行
     * @param amBlock
     */
    public static void  dispatchToMain(AMBlock amBlock) {
        AMEventBusFactory.getDefaultEeventBus().post(new DispatchQueueMessage(amBlock, true));
    }

    public static  void dispatchToMain(final Object target, final Method method, final Object...arg){

        if(target == null || method == null) {
            return;
        }

        AMEventBusFactory.getDefaultEeventBus().post(new DispatchQueueMessage(new AMBlock() {
            @Override
            public void invokeBlock() {
                try {
                    method.invoke(target,arg);
                } catch (Exception e) {
                    throw  new RuntimeException("dispatch Error");
                }
            }
        }, true));

    }

    public static  void dispatchToBackground(final Object target, final Method method, final Object...arg){

        if(target == null || method == null) {
            return;
        }

        AMEventBusFactory.getDefaultEeventBus().post(new DispatchQueueMessage(new AMBlock() {
            @Override
            public void invokeBlock() {
                try {
                    method.invoke(target,arg);
                } catch (Exception e) {
                    throw  new RuntimeException("dispatch Error");
                }
            }
        }, false));

    }

    /**
     * 把消息分发到后台线程执行
     * @param amBlock
     */
    public  static  void dispatchToBackground(AMBlock amBlock) {
        AMEventBusFactory.getDefaultEeventBus().post(new DispatchQueueMessage(amBlock,false));
    }


    /**
     * 后台执行任务
     * @param dispatchQueueMessage
     */
    public void onEventBackgroundThread(DispatchQueueMessage dispatchQueueMessage) {
        if(!dispatchQueueMessage.isMainThread()) {
            dispatchQueueMessage.amBlock.invokeBlock();
        }

    }

    /**
     * 在主线程执行任务
     * @param dispatchQueueMessage
     */
    public void onEventMainThread(DispatchQueueMessage dispatchQueueMessage) {
        if(dispatchQueueMessage.isMainThread()) {
            dispatchQueueMessage.amBlock.invokeBlock();
        }
    }

    /**
     * 分发队列的消息
     */
    public static class DispatchQueueMessage {
        private AMBlock amBlock;
        private boolean mainThread;

        public DispatchQueueMessage(AMBlock amBlock,boolean isMainThread) {
            this.amBlock = amBlock;
            this.mainThread = isMainThread;
        }

        public AMBlock getAmBlock() {

            return amBlock;
        }

        public boolean isMainThread() {
            return mainThread;
        }
    }
}
