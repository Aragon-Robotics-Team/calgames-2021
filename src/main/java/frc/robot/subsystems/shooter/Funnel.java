// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Funnel extends SubsystemBase {
  public static final class Config {
    public static final int kLeftMotorPort = 6;
    public static final int kRightMotorPort = 7;

    public static final double kFunnelSpeed = 0.95;
  }

  private final CANSparkMax m_leftMotor = new CANSparkMax(Config.kLeftMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_rightMotor = new CANSparkMax(Config.kRightMotorPort, MotorType.kBrushless);

  /** Creates a new Funnel. */
  public Funnel() {
    m_leftMotor.setInverted(true);
  }

  private void setMotors(double voltage) {
    m_leftMotor.setVoltage(voltage);
    m_rightMotor.setVoltage(voltage);
  }

  public void setOff() {
    setMotors(0);
  }

  public void setOn() {
    setMotors(Config.kFunnelSpeed);
  }

  public void setReverse() {
    setMotors(-Config.kFunnelSpeed);
  }
}
