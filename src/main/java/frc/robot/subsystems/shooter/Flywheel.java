// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  public static final class Config {
    public static final int kFlyMotorPortMaster = 5;
    public static final int kFlyMotorPortSlave = 7;

    public static final double kGearRatio = 1.0;
    public static final double kEncoderRes = 4096.0;

    public static final double kTargetRPM = 4000.0;
    public static final double kRampUpTime = 5.0; // seconds
    public static final double kRampDownTime = 5.0; // seconds
  }

  private final TalonSRX m_flyMotorMaster = new TalonSRX(Config.kFlyMotorPortMaster);
  private final TalonSRX m_flyMotorSlave = new TalonSRX(Config.kFlyMotorPortSlave);

  /** Creates a new Flywheel. */
  public Flywheel() {
    m_flyMotorMaster.setInverted(false);
    m_flyMotorSlave.setInverted(true);

    m_flyMotorMaster.setNeutralMode(NeutralMode.Coast);
    m_flyMotorSlave.setNeutralMode(NeutralMode.Coast);

    m_flyMotorSlave.follow(m_flyMotorMaster);
  }

  public void setVoltage(double voltage) {
    m_flyMotorMaster.set(TalonSRXControlMode.PercentOutput, voltage);
  }

  public void setOff() {
    setVoltage(0);
  }

  public double getRPM() {
    return -1.0 * m_flyMotorMaster.getSelectedSensorVelocity() * 5.0 * Config.kGearRatio / Config.kEncoderRes * 60;
  }

  public double getVoltage() {
    return m_flyMotorMaster.getMotorOutputVoltage();
  }

  @Override
  public void periodic() {
    super.periodic();

    SmartDashboard.putNumber("Flywheel RPM", getRPM());
    SmartDashboard.putNumber("Flywheel Voltage", m_flyMotorMaster.getMotorOutputVoltage());
  }
}
