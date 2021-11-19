// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RegulateRPM extends CommandBase {
  private final Flywheel m_flywheel;
  private double m_targetRPM;

  /** Creates a new RegulateRPM. */
  public RegulateRPM(Flywheel flywheel) {
    m_flywheel = flywheel;

    addRequirements(flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_targetRPM = SmartDashboard.getNumber("Target RPM", 4000.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double v = m_flywheel.getPercent();
    double difference = Math.abs(m_targetRPM - m_flywheel.getRPM());
    if (difference >= 200) {
      if (m_flywheel.getRPM() > m_targetRPM) {
        m_flywheel.set(v - ((difference) / 2000 / 100));
      } else {
        m_flywheel.set(v + ((difference)) / 1000 / 100);
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
