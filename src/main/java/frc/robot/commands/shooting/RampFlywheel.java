// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RampFlywheel extends CommandBase {
  private final Flywheel m_flywheel;
  private double m_targetRPM;

  /** Creates a new RampFlywheel. */
  public RampFlywheel(Flywheel flywheel) {
    m_flywheel = flywheel;

    addRequirements(flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_targetRPM = SmartDashboard.getNumber("Target RPM", Flywheel.Config.kTargetRPM);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double diff = m_targetRPM - m_flywheel.getRPM();
    m_flywheel.set(m_flywheel.getPercent() + (diff / 100 / 100));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_flywheel.getRPM() - m_targetRPM) <= 25.0;
  }
}
