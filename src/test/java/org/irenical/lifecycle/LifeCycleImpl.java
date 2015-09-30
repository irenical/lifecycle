package org.irenical.lifecycle;

public class LifeCycleImpl implements LifeCycle {
    
    private final boolean startError;
    private final boolean stopError;
    private final boolean isRunningError;
    
    private boolean isRunning;
    
    public LifeCycleImpl(boolean startError, boolean stopError, boolean isRunningError) {
        this.startError=startError;
        this.stopError=stopError;
        this.isRunningError=isRunningError;
    }
    
    @Override
    public void start() throws StartException {
        if(startError){
            throw new StartException("Error on start");
        }
        isRunning=true;
    }
    
    @Override
    public void stop() throws StopException {
        if(stopError){
            throw new StopException("Error on start");
        }
        isRunning=false;
    }
    
    @Override
    public boolean isRunning() throws IsRunningException {
        if(isRunningError){
            throw new IsRunningException("Error on start");
        }
        return isRunning;
    }

}
