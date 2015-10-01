package org.irenical.lifecycle.builder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.irenical.lifecycle.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositeLifeCycle implements LifeCycle {

    private static final Logger LOG = LoggerFactory.getLogger(CompositeLifeCycle.class);

    private final List<LifeCycle> children = new CopyOnWriteArrayList<>();
    
    private final Thread terminator = new Thread(new Runnable() {
        @Override
        public void run() {
            stop();
        }

    }, "Composite LifeCycle shutdown hook");

    public synchronized CompositeLifeCycle append(LifeCycle child) {
        if (child == null) {
            throw new IllegalArgumentException("You cannot append a null LifeCycle");
        } if(unsafeIsRunning()){
            throw new InvalidStateException("You cannot append a child while running");
        }
        children.add(child);
        return this;
    }
    
    public synchronized void withShutdownHook(){
        Runtime.getRuntime().addShutdownHook(terminator);
    }

    @Override
    public synchronized void stop() {
        LOG.info("Stopping Composite LifeCycle");
        for (int i = children.size(); i > 0; --i) {
            LifeCycle hatchling = null;
            try{
                hatchling = children.get(i - 1);
                LOG.info("Stopping LifeCycle '" + hatchling + "'");
                hatchling.stop();
                LOG.info("LifeCycle '" + hatchling + "' was successfully stoped");
            } catch(Exception e){
                LOG.error("Error stoping LifeCycle: " + hatchling + "... ignoring", e);
            }
        }
        children.clear();
    }

    @Override
    public synchronized <ERROR extends Exception> boolean isRunning() throws ERROR {
        return unsafeIsRunning();
    }

    @Override
    public synchronized <ERROR extends Exception> void start() throws ERROR {
        LOG.info("Starting Composite LifeCycle");
        if (unsafeIsRunning()) {
            throw new InvalidStateException("You cannot start a running LifeCycle");
        }
        children.stream().forEach((hatchling) -> hatchling.start());
    }

    private boolean unsafeIsRunning() {
        return children.stream().allMatch((hatchling) -> hatchling.isRunning()) ? !children.isEmpty() : false;
    }

}
