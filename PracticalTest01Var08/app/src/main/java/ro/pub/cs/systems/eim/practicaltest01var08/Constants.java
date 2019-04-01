package ro.pub.cs.systems.eim.practicaltest01var08;


public interface Constants {

    final public static String TAG                  = "practicaltest01var08";
    final public static int NUMBER_OF_CLICKS_THRESHOLD = 4;
    final public static int SERVICE_STOPPED        = 0;
    final public static int SERVICE_STARTED         = 1;

    String BROADCAST_RECEIVER_TAG = "[Message]";
    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01var08.answer",
    };

    final public static boolean DEBUG               = true;

}
