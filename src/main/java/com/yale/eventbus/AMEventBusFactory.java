package com.yale.eventbus;

/**
 * eventbus factory
 */
public class AMEventBusFactory {

    public  static AMEventBus amEventBus;


    public static AMEventBus  getDefaultEeventBus() {

        synchronized (AMEventBusFactory.class) {
            if(amEventBus == null) {
                amEventBus = new AMGreenRobotEventBus();
            }
        }
        return amEventBus;
    }
    
}
