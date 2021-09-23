// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
 * Used during autonomous when the drivetrain is not in use, to surpress errors
 * regarding DifferentialDrive not being updated enough.
 */
public class DiffDriveIdle extends CommandBase {
  private final Drivetrain m_drivetrain;

  /** Creates a new DiffDriveIdle. */
  public DiffDriveIdle(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;

    addRequirements(drivetrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.getDrive().stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
