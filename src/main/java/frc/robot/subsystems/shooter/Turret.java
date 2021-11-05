// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  public static final class Config {
    public static final int kMotorPort = 4;
    public static final double kDegsPerTick = 90.0 / 6900.0;
    public static final double kSpeed = 0.25;
    public static final double kLeftBound = -90.0;
    public static final double kRightBound = 90.0;
  }

  private final TalonSRX m_motor = new TalonSRX(Config.kMotorPort);

  public Turret() {
  }

  public enum TurretState {
    OverLeft, OverRight, Normal
  }

  private TurretState getTurretState() {
    double degs = m_motor.getSelectedSensorPosition() * Config.kDegsPerTick;
    if (degs >= Config.kRightBound) {
      return TurretState.OverRight;
    } else if (degs <= Config.kLeftBound) {
      return TurretState.OverLeft;
    }
    return TurretState.Normal;
  }

  public void setMotor(double v) {
    TurretState state = getTurretState();

    /**
     * Normal: Turret is within "legal" bounds of movement. OverRight: Turret is
     * over +90deg. OverLeft: Turret is under -90deg.
     * 
     * Only move if: the turret is normal (legal), or moving into a legal position
     * (the second two conditions).
     */
    if ((state == TurretState.Normal) || (state == TurretState.OverRight && v >= 0.0)
        || (state == TurretState.OverLeft && v <= 0.0)) {
      m_motor.set(TalonSRXControlMode.PercentOutput, v);
    } else {
      m_motor.set(TalonSRXControlMode.PercentOutput, 0.0);
    }
  }

  public void motorOff() {
    m_motor.set(TalonSRXControlMode.PercentOutput, 0.0);
  }

  public void resetEncoder() {
    m_motor.setSelectedSensorPosition(0.0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Turret Encoder", m_motor.getSelectedSensorPosition() * Config.kDegsPerTick);
    SmartDashboard.putString("Turret State", getTurretState().name());
  }
}
