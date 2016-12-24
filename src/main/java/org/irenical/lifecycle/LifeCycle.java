package org.irenical.lifecycle;

/**
 * Generic lifecycle interface
 * 
 * @author tgsimao
 *
 */
public interface LifeCycle {

  <ERROR extends Exception> void start() throws ERROR;

  <ERROR extends Exception> void stop() throws ERROR;

  <ERROR extends Exception> boolean isRunning() throws ERROR;

}
