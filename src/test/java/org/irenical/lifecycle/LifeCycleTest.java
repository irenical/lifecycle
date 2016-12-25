package org.irenical.lifecycle;

import org.irenical.lifecycle.builder.CompositeLifeCycle;
import org.junit.Assert;
import org.junit.Test;

public class LifeCycleTest {

  private void lifeCycleLoop(LifeCycle lc) {
    Assert.assertFalse(lc.isRunning());
    lc.start();
    Assert.assertTrue(lc.isRunning());
    lc.stop();
    Assert.assertFalse(lc.isRunning());
  }

  @Test
  public void smooth() throws IsRunningException, StartException, StopException {
    lifeCycleLoop(new LifeCycleImpl(false, false, false));
  }

  @Test(expected = StartException.class)
  public void failStart() throws IsRunningException, StartException, StopException {
    lifeCycleLoop(new LifeCycleImpl(true, false, false));
  }

  @Test(expected = IsRunningException.class)
  public void failTest() throws IsRunningException, StartException, StopException {
    lifeCycleLoop(new LifeCycleImpl(false, false, true));
  }

  @Test(expected = StopException.class)
  public void failStop() throws IsRunningException, StartException, StopException {
    lifeCycleLoop(new LifeCycleImpl(false, true, false));
  }

  @Test
  public void testSmoothComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(false, false, false))
        .append(new LifeCycleImpl(false, false, false));
    lifeCycleLoop(c);
  }

  @Test(expected = StartException.class)
  public void testFailStartComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(true, false, false))
        .append(new LifeCycleImpl(false, false, false));
    lifeCycleLoop(c);
  }

  @Test(expected = IsRunningException.class)
  public void testFailTestComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(false, false, true))
        .append(new LifeCycleImpl(false, false, false));
    lifeCycleLoop(c);
  }

  @Test
  public void testFailStopComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(false, true, false))
        .append(new LifeCycleImpl(false, false, false));
    lifeCycleLoop(c);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailDoubleStartComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(false, false, false))
        .append(new LifeCycleImpl(false, false, false));
    c.start();
    c.start();
  }

  @Test(expected = IllegalStateException.class)
  public void testFailAppendAfterStartComposite() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false)).append(new LifeCycleImpl(false, false, false))
        .append(new LifeCycleImpl(false, false, false));
    c.start();
    c.append(new LifeCycleImpl(false, false, false));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testFailAppendNull() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(null);
  }
  
  @Test
  public void testShutdownHook() {
    CompositeLifeCycle c = new CompositeLifeCycle();
    c.append(new LifeCycleImpl(false, false, false));
    c.withShutdownHook();
    c.start();
  }
  
  
}
