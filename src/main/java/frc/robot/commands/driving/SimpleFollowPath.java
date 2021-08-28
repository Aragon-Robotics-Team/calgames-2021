// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import java.util.List;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

/**
 * Path following command that uses lateral and turning PID, not pathweaver +
 * gyro.
 */
public class SimpleFollowPath extends SequentialCommandGroup {
  /**
   * A small "data-class". It simply stores the desired degrees to be turned and
   * distance laterally to be moved.
   */
  public static class PathSegment {
    public final double lateral;
    public final double angle;

    public PathSegment(double angle, double lateral) {
      this.lateral = lateral;
      this.angle = angle;
    }
  }

  /** Creates a new SimpleFollowPath. */
  public SimpleFollowPath(Drivetrain drivetrain, List<PathSegment> segments) {
    Command[] commands = new Command[segments.size() * 2];

    // Two commands per segment, one to rotate, one to move laterally
    for (int i = 0; i < segments.size(); i++) {
      PathSegment s = segments.get(i);
      commands[i * 2] = new TurnWithPID(drivetrain, s.angle);
      commands[i * 2 + 1] = new MoveWithPID(drivetrain, s.lateral);
    }

    addCommands(commands);
  }
}
