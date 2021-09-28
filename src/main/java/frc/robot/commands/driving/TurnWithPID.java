// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.EncoderCalculationType;

public class TurnWithPID extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final PIDController m_pid = new PIDController(Drivetrain.Config.kPTurn, Drivetrain.Config.kITurn,
      Drivetrain.Config.kDTurn);

  /** Creates a new TurnWithPID. */
  public TurnWithPID(Drivetrain drivetrain, double degrees) {
    m_drivetrain = drivetrain;

    addRequirements(drivetrain);

    m_pid.setSetpoint(degrees);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetEncoder();
    m_drivetrain.setEncoderState(EncoderCalculationType.Turn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_pid.calculate(m_drivetrain.getDistance());

    // See execute method in {@link MoveWithPID} for why this is necessary
    double rSpeed = MathUtil.clamp(speed, -1.0, 1.0);
    rSpeed *= 0.5;

    m_drivetrain.getDrive().arcadeDrive(0.0, rSpeed);
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
