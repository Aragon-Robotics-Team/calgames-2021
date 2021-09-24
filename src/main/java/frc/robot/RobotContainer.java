/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.driving.ArcadeDrive;
import frc.robot.commands.driving.DiffDriveIdle;
import frc.robot.commands.driving.SimpleFollowPath;
import frc.robot.commands.driving.SimpleFollowPath.PathSegment;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static final class Config {
    public static final int kJoystickPort = 4;
  }

  // OI
  private final Joystick m_joystick = new Joystick(Config.kJoystickPort);

  // Subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  // Commands
  private final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_joystick);
  private final SimpleFollowPath m_simpleFollowPath = new SimpleFollowPath(m_drivetrain,
      new ArrayList<PathSegment>(Arrays.asList(new PathSegment(0.0, 5.0), new PathSegment(0.0, -5.0))));
  private final DiffDriveIdle m_diffDriveIdle = new DiffDriveIdle(m_drivetrain);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_drivetrain.resetEncoder();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to get the teleop command in the Robot class
   * 
   * @return the teleop command
   */
  public Command getTeleopCommand() {
    m_drivetrain.setDefaultCommand(m_arcadeDrive);
    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_drivetrain.setDefaultCommand(m_diffDriveIdle);
    return m_simpleFollowPath;
  }

  /**
   * Use this to access the joystick device
   * 
   * @return the Joystick instance
   */
  public Joystick getJoystick() {
    return m_joystick;
  }
}
