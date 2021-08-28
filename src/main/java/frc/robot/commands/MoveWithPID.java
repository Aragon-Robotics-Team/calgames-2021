// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveWithPID extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final PIDController m_pid = new PIDController(Drivetrain.Config.kP, Drivetrain.Config.kI,
      Drivetrain.Config.kD);

  /** Creates a new MoveWithPID. */
  public MoveWithPID(Drivetrain drivetrain, double setpoint) {
    m_drivetrain = drivetrain;

    addRequirements(drivetrain);

    m_pid.setSetpoint(setpoint);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_pid.calculate(m_drivetrain.getDistance());
    m_drivetrain.getDrive().tankDrive(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.getDrive().stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_pid.atSetpoint();
  }
}
