// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rollers extends SubsystemBase {
  public static final class Config {
    public static final int kRollerMotor = 4;
    public static final double kRampTime = 1.0;
    public static final double kVoltsFull = 12.0;
  }

  private CANSparkMax m_motor = new CANSparkMax(Config.kRollerMotor, MotorType.kBrushless);

  /** Creates a new Rollers. */
  public Rollers() {
    m_motor.setInverted(true);

    m_motor.setOpenLoopRampRate(Config.kRampTime);
    m_motor.setClosedLoopRampRate(Config.kRampTime);

    m_motor.setIdleMode(IdleMode.kCoast);
  }

  public void setOn() {
    m_motor.setVoltage(Config.kVoltsFull);
  }

  public void setOff() {
    m_motor.setVoltage(0);
  }
}
