// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Turret;

public class RunTurret extends CommandBase {
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
    // int direction = m_joystick.getPOV(0);

    double rawTurn = -m_joystick.getRawAxis(2);
    System.out.println(rawTurn);
    m_turret.setMotor(MathUtil.clamp(rawTurn, -0.2, 0.2));
    return;
    // double turn = Math.abs(rawTurn) < 0.2 ? 0.0 : rawTurn;

    // if (turn > 0) {
    // System.out.println("moving right");
    // // m_turret.runMotor(Direction.Right);
    // } else if (turn < 0) {
    // System.out.println("moving left");
    // m_turret.runMotor(Direction.Left);
    // } else {
    // m_turret.motorOff();
    // }
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
