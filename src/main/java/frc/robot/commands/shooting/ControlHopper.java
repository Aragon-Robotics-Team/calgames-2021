// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.shooter.Funnel;
import frc.robot.subsystems.shooter.Tower;

public class ControlHopper extends CommandBase {
  private final Tower m_tower;
  private final Funnel m_funnel;

  private final JoystickButton m_shootButton;
  private final JoystickButton m_offButton;

  /** Creates a new ControlHopper. */
  public ControlHopper(Tower tower, Funnel funnel, JoystickButton shoot, JoystickButton off) {
    m_tower = tower;
    m_funnel = funnel;

    m_shootButton = shoot;
    m_offButton = off;

    addRequirements(tower, funnel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_shootButton.get()) {
      m_tower.setOn();
      m_funnel.setReverse();
      return;
    }

    m_tower.setOff();
    m_funnel.setOff();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_tower.setOff();
    m_funnel.setOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_offButton.get();
  }
}
