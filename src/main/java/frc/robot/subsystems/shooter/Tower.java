// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tower extends SubsystemBase {
  public static final class Config {
    public static final int kMotorPort = 1;
    public static final double kMotorSpeed = 0.75;
  }

  private final TalonSRX m_motor = new TalonSRX(Config.kMotorPort);

  private void setMotor(double value) {
    m_motor.set(TalonSRXControlMode.PercentOutput, value);
  }

  public void setOff() {
    setMotor(0);
  }

  public void setOn() {
    setMotor(Config.kMotorSpeed);
  }

  public void setReverse() {
    setMotor(-Config.kMotorSpeed);
  }
}
