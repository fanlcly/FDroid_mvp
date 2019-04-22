package fancy.com.rxmvp.event;

/**
 * RxBusImpl的单例实现
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public class BusProvider {
    private static RxBusImpl bus;

    public static RxBusImpl getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = RxBusImpl.get();
                }
            }
        }
        return bus;
    }

}
