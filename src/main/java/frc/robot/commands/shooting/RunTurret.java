// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.shooter.Turret;

public class RunTurret extends CommandBase {
  public static final class Config {
    public static final double kMaxSpeed = 0.2;
  }

  private final Joystick m_joystick;
  private final Turret m_turret;

  /** Creates a new RunTurret. */
  public RunTurret(Joystick joystick, Turret turret) {
    m_joystick = joystick;
    m_turret = turret;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rawTurn = -m_joystick.getRawAxis(2);
    m_turret.setMotor(MathUtil.clamp(rawTurn, -Config.kMaxSpeed, Config.kMaxSpeed));
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
