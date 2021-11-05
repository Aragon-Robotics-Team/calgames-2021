// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.shooter.Turret;

public class TurnTowardTarget extends CommandBase {
  public static final class Config {
    public static final double kTargetAngle = 0.0; // In degrees.
    public static final double kMoveSpeed = 0.2;
    public static final double kTolerance = 1.0;
  }

  private final Limelight m_limelight;
  private final Turret m_turret;

  /** Creates a new TurnTowardTarget. */
  public TurnTowardTarget(Limelight limelight, Turret turret) {
    m_limelight = limelight;
    m_turret = turret;

    addRequirements(limelight, turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Turning Toward Target", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = m_limelight.getTx();

    double curbedSpeed = (Math.abs(angle) / 27) * Config.kMoveSpeed;
    if (angle < Config.kTargetAngle) {
      m_turret.setMotor(curbedSpeed);
    } else {
      m_turret.setMotor(-curbedSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.motorOff();
    SmartDashboard.putBoolean("Turning Toward Target", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return Math.abs(m_limelight.getTx()) < Config.kTolerance;
    return false;
  }
}
