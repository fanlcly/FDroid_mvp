package com.fancy.rxmvp.event;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */

public interface IBus {

    void register(Object object);

    void unregister(Object object);

    void post(AbsEvent event);

    void postSticky(AbsEvent event);


    abstract class AbsEvent {
        public abstract int getTag();
    }

}
