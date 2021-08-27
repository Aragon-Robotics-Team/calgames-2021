// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public static final class Config {
    public static final int kLeftMotorMaster = 8;
    public static final int kRightMotorMaster = 5;
    public static final int kLeftMotorSlave = 1;
    public static final int kRightMotorSlave = 2;
  }

  private CANSparkMax leftMotorMaster = new CANSparkMax(Config.kLeftMotorMaster, MotorType.kBrushless);
  private CANSparkMax rightMotorMaster = new CANSparkMax(Config.kRightMotorMaster, MotorType.kBrushless);
  private CANSparkMax leftMotorSlave = new CANSparkMax(Config.kLeftMotorSlave, MotorType.kBrushless);
  private CANSparkMax rightMotorSlave = new CANSparkMax(Config.kRightMotorSlave, MotorType.kBrushless);

  private DifferentialDrive drive = new DifferentialDrive(leftMotorMaster, rightMotorMaster);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Set masters to inverted
    leftMotorMaster.setInverted(true);
    rightMotorMaster.setInverted(true);
    // Enable following
    leftMotorSlave.follow(leftMotorMaster);
    rightMotorSlave.follow(rightMotorMaster);
  }

  /**
   * Other classes can use this method to use the DifferentialDrive instance.
   */
  public DifferentialDrive getDrive() {
    return drive;
  }
}
