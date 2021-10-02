// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RegulateRPM extends CommandBase {
  private final Flywheel m_flywheel;

  /** Creates a new RegulateRPM. */
  public RegulateRPM(Flywheel flywheel) {
    m_flywheel = flywheel;

    addRequirements(flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(m_flywheel.getRPM() - Flywheel.Config.kTargetRPM) >= 200) {
      if (m_flywheel.getRPM() > Flywheel.Config.kTargetRPM) {
        m_flywheel.setVoltage(m_flywheel.getVoltage() * -0.9);
      } else {
        m_flywheel.setVoltage(m_flywheel.getVoltage() * 1.0005);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
