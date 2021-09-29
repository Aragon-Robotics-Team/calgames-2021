// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RampDownFlywheel extends CommandBase {
  private final Flywheel m_flywheel;
  private final double m_targetRPM = 0.0;

  /** Creates a new RampDownFlywheel. */
  public RampDownFlywheel(Flywheel flywheel) {
    m_flywheel = flywheel;

    addRequirements(flywheel);
  }

  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flywheel.setVoltage(m_flywheel.getVoltage() * 0.75 * -1.0);
  }

  @Override
  public void end(boolean interrupted) {
    m_flywheel.setOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // 100 RPM tolerance (turn off immediately once 100 rpm has been reached)
    return Math.abs(m_flywheel.getRPM() - m_targetRPM) <= 100;
  }
}
