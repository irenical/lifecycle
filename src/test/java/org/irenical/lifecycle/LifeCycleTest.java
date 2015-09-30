package org.irenical.lifecycle;

import org.junit.Assert;
import org.junit.Test;

public class LifeCycleTest {

    @Test
    public void smooth() throws IsRunningException, StartException, StopException {
        LifeCycleImpl lc = new LifeCycleImpl(false, false, false);
        Assert.assertFalse(lc.isRunning());
        lc.start();
        Assert.assertTrue(lc.isRunning());
        lc.stop();
        Assert.assertFalse(lc.isRunning());
    }

}
