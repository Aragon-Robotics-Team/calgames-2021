// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.shooter.Flywheel;
import frc.robot.subsystems.shooter.Funnel;
import frc.robot.subsystems.shooter.Tower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ControlShooter extends SequentialCommandGroup {
  /** Creates a new ControlShooter. */
  public ControlShooter(Flywheel flywheel, Tower tower, Funnel funnel, JoystickButton shootOnButton,
      JoystickButton shootOffButton, JoystickButton shootReverseButton) {
    Command rampFlywheel = new RampFlywheel(flywheel);
    Command controlHopper = new ControlHopper(tower, funnel, shootOnButton, shootOffButton, shootReverseButton);
    Command regulateRPM = new RegulateRPM(flywheel);
    Command rampDownFlywheel = new RampDownFlywheel(flywheel);

    addCommands(rampFlywheel, new ParallelRaceGroup(controlHopper, regulateRPM), rampDownFlywheel);
  }
}
