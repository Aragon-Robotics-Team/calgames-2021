// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Flywheel;

public class RampDownFlywheel extends CommandBase {
  public static final class Config {
    public static final double kP = 0.25;
  }

  private final Flywheel m_flywheel;

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
    double error = -m_flywheel.getRPM();
    m_flywheel.set(m_flywheel.getPercent() + (error * Config.kP));
  }

  @Override
  public void end(boolean interrupted) {
    m_flywheel.setOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_flywheel.getRPM() <= 100.0; // At this point, flywheel is running slow enough that it is safe to turn off
                                         // immediately.
  }
}
