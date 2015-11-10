# LifeCycle
Generic lightweight life cycle library for Java

## Purpose
Objects with a somewhat complex state might have some kind of life cycle pattern, usually consisting of startup and shutdown behaviour. You can find this type of objects in several application frameworks (android.app.Activity, java.applet.Applet, javax.servlet.ServletContextListener, etc...).  
Although all of these objects have similar signatures, there is no standard way for you to expose a class as being "lifecyclish". Which might be very helpful if you want a stateful object of yours to be used in some other project.

## Practical use
Let's say you have an object that knows how to setup and teardown a database connection, to be used by other developers in some application. By implementing org.irenical.lifecycle.LifeCycle you're implying your object must be setup before use and teardown after it's no longer needed.

```java
public class MyDAO implements LifeCycle {

  @Override
  void start(){
    //...(re)init code
  }

  @Override
  void stop(){
     //...shutdown code
  }
  
  @Override
  boolean boolean isRunning(){
    //...health check code
  }

}
```
The supposed other developer, in his/her application can then control yours and other LifeCycle objects regardless of their actual classes.  

## Multiple LifeCycles
A composite LifeCycle implementation is also provided in this library to easily handle startup/shutdown of multiple lifecycles. Assuming you have a bunch of LifeCycle instances, you can do this.
```java
CompositeLifeCycle life = new CompositeLifeCycle();
life.append(myLifecycle1);
life.append(myLifecycle2);
life.append(myLifecycle3);
life.start();
```

## Bootstraping
If you're developing an application bootstrap, you can setup a CompositeLifeCycle with a shutdown hook, so that your application can graciously be teardown before the JVM shutdown.
```java
public class Main {

  public static void main(String[] args) {
    try {
      
      MyLogger logger = new MyLogger();
      MyDAO dao = new MyDAO();
      MyServer server = new MyServer();
      
      CompositeLifeCycle life = new CompositeLifeCycle();
      life.append(logger);
      life.append(dao);
      life.append(server);
      life.withShutdownHook();
      life.start();

    } catch (Exception e) {
      // application boot failure
    }
  }

}

```
