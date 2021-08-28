# Simple Navigation

In order to navigate semi-precisely without an outside sensor, use the `SimpleFollowPath` class. 
It contains a subclass called `PathSegment`, which represents a single "vector" (but not implemented as a vector) on the path.

The constructor takes in two `doubles`, the first being the degrees to be turned for that segment, the next being the feet to be moved forward/backward. 

`SimpleFollowPath` uses the `TurnWithPID` and `MoveWithPID` commands to move the robot along the path.
It runs a sequential command group containing all the commands needed.

`TurnWithPID` and `MoveWithPID` utilize the `PIDController` class. All encoder distance calculations are done within the `Drivetrain` class. 
In order to figure out what to calculate (feet moved or degrees turned), the drivetrain lets the caller set an `EncoderCalculationType` with the `setEncoderState` method. 
The `EncoderCalculationType` enum has two values: `Lateral` and `Turn`, which are used to determine which equation to use for the distance.