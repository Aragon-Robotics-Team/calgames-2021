/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.driving.ArcadeDrive;
import frc.robot.commands.driving.DiffDriveIdle;
import frc.robot.commands.driving.SimpleFollowPath;
import frc.robot.commands.driving.SimpleFollowPath.PathSegment;
import frc.robot.commands.shooting.ControlShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.shooter.Flywheel;
import frc.robot.subsystems.shooter.Funnel;
import frc.robot.subsystems.shooter.Tower;

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

    // TODO configure buttons
    public static final int kShootButton = 0;
    public static final int kShootOffButton = 0;
    public static final int kFlywheelButton = 0;
  }

  // OI
  private final Joystick m_joystick = new Joystick(Config.kJoystickPort);
  private final JoystickButton m_shootButton = new JoystickButton(m_joystick, Config.kShootButton);
  private final JoystickButton m_shootOffButton = new JoystickButton(m_joystick, Config.kShootOffButton);
  private final JoystickButton m_flywheelButton = new JoystickButton(m_joystick, Config.kFlywheelButton);
  // Subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Flywheel m_flywheel = new Flywheel();
  private final Tower m_tower = new Tower();
  private final Funnel m_funnel = new Funnel();
  // Commands
  private final Command m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_joystick);
  private final Command m_diffDriveIdle = new DiffDriveIdle(m_drivetrain);

  private final Command m_controlShooter = new ControlShooter(m_flywheel, m_tower, m_funnel, m_shootButton,
      m_shootOffButton);

  private final Command m_simpleFollowPath = new SimpleFollowPath(m_drivetrain, new PathSegment(0.0, 5.0),
      new PathSegment(0.0, -5.0));

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
    m_flywheelButton.whenPressed(m_controlShooter);
  }

  /**
   * Use this to get the teleop command in the Robot class
   * 
   * @return the teleop command
   */
  public Command getTeleopCommand() {
    m_drivetrain.setCoastMode();
    m_drivetrain.setDefaultCommand(m_arcadeDrive);

    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_drivetrain.setBrakeMode();
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
