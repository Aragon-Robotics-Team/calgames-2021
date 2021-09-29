// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  public static final class Config {
    public static final int kPCMId = 2;
    public static final int kForwardChannel = 1;
    public static final int kReverseChannel = 6;
  }

  private final DoubleSolenoid m_solenoidLeft = new DoubleSolenoid(Config.kPCMId, Config.kForwardChannel,
      Config.kReverseChannel);

  private void set(Value v) {
    m_solenoidLeft.set(v);
  }

  public void armOut() {
    set(Value.kForward);
  }

  public void armIn() {
    set(Value.kReverse);
  }
}
