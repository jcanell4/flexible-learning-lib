package org.elsquatrecaps.flexiblelearning.eventactivity.responses;

/**
 *
 * @author josep
 */
public class ResponseTimer extends DataEventResponse{
    private Timer nextTimer=null;
    

    public ResponseTimer() {
    }

    public ResponseTimer(CallableJavascript onReciveCallable) {
        super(onReciveCallable);
    }
    
    public ResponseTimer(Timer nextTimer) {
        this.nextTimer = nextTimer;
    }
    
    public ResponseTimer(CallableJavascript onReciveCallable, Timer nextTimer) {
        super(onReciveCallable);
        this.nextTimer = nextTimer;
    }
    
    /**
     * @return the nextTimer
     */
    public Timer getNextTimer() {
        return nextTimer;
    }

    /**
     * @param nextTimer the nextTimer to set
     */
    public void setNextTimer(Timer nextTimer) {
        this.nextTimer = nextTimer;
    }
}
