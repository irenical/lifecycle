[![][maven img]][maven]
[![][travis img]][travis]
[![][codecov img]][codecov]

# LifeCycle
Generic lightweight life cycle library for Java

## Purpose
Objects with a somewhat complex state might have some kind of life cycle pattern, usually consisting of startup and shutdown behaviour. You can find this type of objects in several application frameworks (android.app.Activity, java.applet.Applet, javax.servlet.ServletContextListener, etc...).  
Although all of these objects have similar signatures, there is no standard way for you to expose a class as being "lifecyclish".  
This project tries to address this by offering a very simple life cycle interface and minor common functionality, such as a composite pattern implementation.

## Practical use
Let's say you have an object that knows how to setup and teardown a database connection, to be included in a library and later on used to develop one or more applications. By implementing org.irenical.lifecycle.LifeCycle you're not only implying your object must be setup before use and teardown after it's no longer needed, but also allowing the downstream application's developer to do so in a generic manner, in no way specific to your library.

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

## Multiple LifeCycles
A composite LifeCycle implementation is also provided in this library to easily handle startup/shutdown of multiple lifecycles. Assuming you have a bundle of LifeCycle instances, you can do this.
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
[maven]:http://search.maven.org/#search|gav|1|g:"org.irenical.lifecycle"%20AND%20a:"lifecycle"
[maven img]:https://maven-badges.herokuapp.com/maven-central/org.irenical.lifecycle/lifecycle/badge.svg

[travis]:https://travis-ci.org/irenical/lifecycle
[travis img]:https://travis-ci.org/irenical/lifecycle.svg?branch=master

[codecov]:https://codecov.io/gh/irenical/lifecycle
[codecov img]:https://codecov.io/gh/irenical/lifecycle/branch/master/graph/badge.svg
