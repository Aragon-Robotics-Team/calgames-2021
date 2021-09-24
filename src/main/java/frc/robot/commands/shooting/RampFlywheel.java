// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RampFlywheel extends CommandBase {
  private final Flywheel m_flywheel;
  private final double m_targetVoltage;
  private final double m_targetTime;

  private SlewRateLimiter m_rateLimit;

  /** Creates a new RampFlywheel. */
  public RampFlywheel(Flywheel flywheel, double targetVoltage, double targetTime) {
    m_flywheel = flywheel;
    m_targetVoltage = targetVoltage;
    m_targetTime = targetTime;

    addRequirements(flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rateLimit = new SlewRateLimiter(1 / m_targetTime, m_flywheel.getVoltage());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flywheel.setVoltage(m_rateLimit.calculate(m_targetVoltage));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // 0.05 voltage tolerance
    return Math.abs(m_flywheel.getVoltage() - m_targetVoltage) < 0.05;
  }
}
