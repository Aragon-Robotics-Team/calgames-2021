// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class FindTarget extends CommandBase {
  public static final class Config {
    public static final double kTargetDistance = 14.0; // In feet
    public static final double kMoveSpeed = 0.5;
    public static final double kTolerance = 0.2;

  }

  private final Limelight m_limelight;
  private final Drivetrain m_drivetrain;

  /** Creates a new FindShooter. */
  public FindTarget(Limelight limelight, Drivetrain drivetrain) {
    m_limelight = limelight;
    m_drivetrain = drivetrain;

    addRequirements(limelight, drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = m_limelight.calcDistance();

    if (distance < Config.kTargetDistance) {
      m_drivetrain.getDrive().tankDrive(-Config.kMoveSpeed, -Config.kMoveSpeed, false);
    } else {
      m_drivetrain.getDrive().tankDrive(Config.kMoveSpeed, Config.kMoveSpeed, false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_limelight.calcDistance() - Config.kTargetDistance) < Config.kTolerance;
  }
}
