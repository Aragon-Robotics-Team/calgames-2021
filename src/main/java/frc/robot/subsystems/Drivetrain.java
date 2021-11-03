// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public static final class Config {
    public static final int kLeftMotorMaster = 1;
    public static final int kRightMotorMaster = 2;

    public static final int kEncoderChanA = 0;
    public static final int kEncoderChanB = 1;

    public static final double kFeetPerRotation = 0.5 * Math.PI;
    public static final double kPulsesPerRotation = 128.0;

    public static final double kDegsPerTick = 360.0 / 42.0;
    public static final double kGearRatio = 1.0 / 10.0;

    public static final double kP = 0.5;
    public static final double kI = 1e-1;
    public static final double kD = 1e-1;

    public static final double kPTurn = 0.8;
    public static final double kITurn = 1e-5;
    public static final double kDTurn = 1e-5;
  }

  private CANSparkMax m_leftMotorMaster = new CANSparkMax(Config.kLeftMotorMaster, MotorType.kBrushless);
  private CANSparkMax m_rightMotorMaster = new CANSparkMax(Config.kRightMotorMaster, MotorType.kBrushless);

  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorMaster, m_rightMotorMaster);

  private CANEncoder m_encRight = m_rightMotorMaster.getEncoder();
  private CANEncoder m_encLeft = m_leftMotorMaster.getEncoder();

  public enum EncoderCalculationType {
    Lateral, Turn
  }

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Set masters to inverted
    m_leftMotorMaster.setInverted(true);
    m_rightMotorMaster.setInverted(true);

    // Set encoder distance constant
    m_encLeft.setPositionConversionFactor(1.0);
    m_encRight.setPositionConversionFactor(1.0);
  }

  /**
   * Use this to access the DifferentialDrive instance of the drivetrain.
   * 
   * @return the differential drive instance
   */
  public DifferentialDrive getDrive() {
    return m_drive;
  }

  /**
   * Set motors to brake mode.
   */
  public void setBrakeMode() {
    setMotorMode(IdleMode.kBrake);
  }

  /**
   * Set motors to coast mode.
   */
  public void setCoastMode() {
    setMotorMode(IdleMode.kCoast);
  }

  /**
   * Sets the idle mode on all 4 motors.
   * 
   * @param mode either IdleMode.kCoast or kBrake
   */
  private void setMotorMode(IdleMode mode) {
    m_leftMotorMaster.setIdleMode(mode);
    m_rightMotorMaster.setIdleMode(mode);
  }

  /**
   * Calculates the distance driven using the encoder.
   * 
   * @return Distance in feet/angles depending on the calculation mode set with
   *         setEncoderState (default is lateral)
   */
  public double getDistance() {
    return m_encRight.getPosition();
  }

  /**
   * Sets the encoder calculation mode.
   * 
   * @param e Can be either EncoderCalculationType.Lateral or
   *          EncoderCalculationType.Turn
   */
  public void setEncoderState(EncoderCalculationType e) {
    switch (e) {
      case Lateral:
        m_encLeft.setPositionConversionFactor(Config.kGearRatio * Config.kFeetPerRotation);
        m_encRight.setPositionConversionFactor(Config.kGearRatio * Config.kFeetPerRotation);
        break;
      case Turn:
        m_encLeft.setPositionConversionFactor(Config.kDegsPerTick);
        m_encRight.setPositionConversionFactor(Config.kDegsPerTick);
        break;
    }
  }

  /**
   * Resets the encoder value
   */
  public void resetEncoder() {
    m_encLeft.setPosition(0);
    m_encRight.setPosition(0);
  }

  @Override
  public void periodic() {
    super.periodic();

    SmartDashboard.putNumber("Drivetrain Left", m_encLeft.getPosition());
    SmartDashboard.putNumber("Drivetrain Right", m_encRight.getPosition());
  }
}
