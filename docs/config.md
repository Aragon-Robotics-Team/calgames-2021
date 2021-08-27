# How to handle storing configuration details/constants

I have decided on not using a `Constants.java` class like we did last year, it's not scalable, and the file
gets very messy, very quickly. Another option was the extremely complex system Daniel had. I've decided to go with
a system that lays somewhere in between those two extremes. In each subsystem, create a static class to store constants.
If multiple classes need to share constants, put the constants in whatever class instantiates those classes.

## Example

```java
public class MySubsystem extends SubsystemBase {
    public static final class Config {
        public static final int kConfigVariable = 100;
    }

    // subsystem code down here ...
}
```

Also, use the `kName` naming convention for naming constants (from the google coding style guide and its what wpilib uses).
