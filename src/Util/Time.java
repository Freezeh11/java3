package Util;

/**
 * This class served for readability purposes only
 */
public class Time {
    private static final double timeStarted = System.nanoTime();

    public static double getTime(){
        return (System.nanoTime() - timeStarted) * 1E-9;
    }
}
