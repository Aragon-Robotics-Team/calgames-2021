// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.EncoderCalculationType;

public class MoveWithPID extends CommandBase {
  private final Drivetrain m_drivetrain;
  private double initTime;
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
    m_drivetrain.setEncoderState(EncoderCalculationType.Lateral);
    m_drivetrain.resetEncoder();
    // initTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_pid.calculate(m_drivetrain.getDistance());
    double rSpeed = MathUtil.clamp(speed, -1.0, 1.0);
    rSpeed *= -0.5;
    // m_drivetrain.getDrive().arcadeDrive(rSpeed, 0.0, false);
    // m_drivetrain.getDrive().tankDrive(0.25, 0.25, false);
    m_drivetrain.getDrive().tankDrive(rSpeed, rSpeed, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.getDrive().stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return Timer.getFPGATimestamp() - initTime >= 2.0;
    return m_pid.atSetpoint();
  }
}
