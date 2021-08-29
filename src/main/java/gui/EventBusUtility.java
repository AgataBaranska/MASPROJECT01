package gui;

import com.google.common.eventbus.EventBus;

public class EventBusUtility {
    private static EventBus eventBus;

    private EventBusUtility() {
    }

    public static synchronized EventBus getEventBus(){
        if(eventBus ==null){
            eventBus= new EventBus();
        }
        return eventBus;
    }
}
