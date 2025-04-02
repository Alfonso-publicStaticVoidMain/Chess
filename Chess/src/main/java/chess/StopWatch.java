package chess;

/**
 * Class is not mine, it's credited to StackOverflow user: user1007522
 * https://stackoverflow.com/questions/8255738/is-there-a-stopwatch-in-java#8255762
 * With the modification to use nanoTime() given by user Nikita Bosik.
 * @author user1007522
 * @author Nikita Bosik
 */
public class StopWatch {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.nanoTime();
        this.running = false;
    }

    //elapssed time in nanoseconds
    public long getElapsedTime() {
        return running ? System.nanoTime() - startTime : stopTime - startTime;
//        long elapsed;
//        if (running) {
//            elapsed = (System.nanoTime() - startTime);
//        } else {
//            elapsed = (stopTime - startTime);
//        }
//        return elapsed;
    }

    //elapssed time in seconds
    public long getElapsedTimeSecs() {
        return running ? (System.nanoTime() - startTime) / 1000000000 : (stopTime - startTime) / 1000000000;
//        long elapsed;
//        if (running) {
//            elapsed = ((System.nanoTime() - startTime) / 1000000000);
//        } else {
//            elapsed = ((stopTime - startTime) / 1000000000);
//        }
//        return elapsed;
    }
}
