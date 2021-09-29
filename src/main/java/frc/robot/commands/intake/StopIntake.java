// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.intake.Rollers;

public class StopIntake extends InstantCommand {
  private final Rollers m_rollers;

  /** Creates a new StopIntake. */
  public StopIntake(Rollers rollers) {
    m_rollers = rollers;

    addRequirements(rollers);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rollers.setOff();
  }
}
