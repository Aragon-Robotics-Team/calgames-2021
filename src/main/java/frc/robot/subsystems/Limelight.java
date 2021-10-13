// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  public static final class Config {
    public static final double kPipeline = 1;

    public static final double kTargetHeight = 8.0;
    public static final double kMountingHeight = 2.083;
    public static final double kMountingAngle = 45.0;
  }

  private NetworkTable m_table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry m_tv = m_table.getEntry("tv");
  private NetworkTableEntry m_tx = m_table.getEntry("tx");
  private NetworkTableEntry m_ty = m_table.getEntry("ty");
  private NetworkTableEntry m_ta = m_table.getEntry("ta");

  /** Creates a new Limelight. */
  public Limelight() {
    m_table.getEntry("pipeline").setDouble(1);
  }

  public double getTv() {
    return m_tv.getDouble(0);
  }

  public double getTx() {
    return m_tx.getDouble(0);
  }

  public double getTy() {
    return m_ty.getDouble(0);
  }

  public double getTa() {
    return m_ta.getDouble(0);
  }

  public double calcDistance() {
    return (Config.kTargetHeight - Config.kMountingHeight) / Math.tan(Math.toRadians(Config.kMountingAngle + getTy()));
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Limelight tv", getTv());
    SmartDashboard.putNumber("Limelight tx", getTx());
    SmartDashboard.putNumber("Limelight ty", getTy());
    SmartDashboard.putNumber("Limelight ta", getTa());
  }
}
