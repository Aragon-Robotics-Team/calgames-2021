// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
  public static final class Config {
    public static final int kPCMId = 2;
    public static final int kForwardChannel = 2;
    public static final int kReverseChannel = 5;
  }

  private final DoubleSolenoid m_solenoid = new DoubleSolenoid(Config.kPCMId, Config.kForwardChannel,
      Config.kReverseChannel);

  private void set(Value v) {
    m_solenoid.set(v);
  }

  public void hoodUp() {
    set(Value.kForward);
  }

  public void hoodDown() {
    set(Value.kReverse);
  }
}
