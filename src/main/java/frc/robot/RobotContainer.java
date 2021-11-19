/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.driving.ArcadeDrive;
import frc.robot.commands.driving.DiffDriveIdle;
import frc.robot.commands.intake.ArmDown;
import frc.robot.commands.intake.ArmUp;
import frc.robot.commands.intake.RollIntake;
import frc.robot.commands.intake.StopIntake;
import frc.robot.commands.shooting.ControlShooter;
import frc.robot.commands.shooting.FindTarget;
import frc.robot.commands.shooting.RunTurret;
import frc.robot.commands.shooting.TurnTowardTarget;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.intake.Arm;
import frc.robot.subsystems.intake.Rollers;
import frc.robot.subsystems.shooter.Flywheel;
import frc.robot.subsystems.shooter.Funnel;
import frc.robot.subsystems.shooter.Hood;
import frc.robot.subsystems.shooter.Tower;
import frc.robot.subsystems.shooter.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static final class Config {
    public static final int kJoystickDriverPort = 4;
    public static final int kJoystickShooterPort = 5;
    public static final int kJoystickSafePort = 3;

    public static final int kShootButton = 8;
    public static final int kShootOffButton = 4;
    public static final int kIntakeUpButton = 1;
    public static final int kIntakeDownButton = 2;
    public static final int kFlywheelButton = 3;
    public static final int kRollersOnButton = 5;
    public static final int kRollersOffButton = 6;
    public static final int kShootReverseButton = 1;
    public static final int kMoveFromTargetButton = 7;
    public static final int kHoodDownButton = 7;
    public static final int kHoodUpButton = 5;
  }

  // OI
  private final Joystick m_driverJoystick = new Joystick(Config.kJoystickDriverPort);
  private final Joystick m_shooterJoystick = new Joystick(Config.kJoystickShooterPort);
  private final Joystick m_safeJoystick = new Joystick(Config.kJoystickSafePort);

  private final JoystickButton m_shootButton = new JoystickButton(m_shooterJoystick, Config.kShootButton);
  private final JoystickButton m_shootOffButton = new JoystickButton(m_shooterJoystick, Config.kShootOffButton);
  private final JoystickButton m_flywheelButton = new JoystickButton(m_shooterJoystick, Config.kFlywheelButton);
  private final JoystickButton m_armUpButton = new JoystickButton(m_driverJoystick, Config.kIntakeUpButton);
  private final JoystickButton m_armDownButton = new JoystickButton(m_driverJoystick, Config.kIntakeDownButton);
  private final JoystickButton m_rollerOnButton = new JoystickButton(m_driverJoystick, Config.kRollersOnButton);
  private final JoystickButton m_rollerOffButton = new JoystickButton(m_driverJoystick, Config.kRollersOffButton);
  private final JoystickButton m_shooterReverseButton = new JoystickButton(m_shooterJoystick,
      Config.kShootReverseButton);
  private final JoystickButton m_hoodDownButton = new JoystickButton(m_shooterJoystick, Config.kHoodDownButton);
  private final JoystickButton m_hoodUpButton = new JoystickButton(m_shooterJoystick, Config.kHoodUpButton);
  // Subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Flywheel m_flywheel = new Flywheel();
  private final Tower m_tower = new Tower();
  private final Funnel m_funnel = new Funnel();
  private final Arm m_arm = new Arm();
  private final Rollers m_rollers = new Rollers();
  private final Limelight m_limelight = new Limelight();
  private final Turret m_turret = new Turret();
  private final Hood m_hood = new Hood();
  // Commands
  private final Command m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_driverJoystick);
  private final Command m_safeArcadeDrive = new ArcadeDrive(m_drivetrain, m_safeJoystick, 0.5, 0.7);
  private final Command m_diffDriveIdle = new DiffDriveIdle(m_drivetrain);

  private final Command m_controlShooter = new ControlShooter(m_flywheel, m_tower, m_funnel, m_shootButton,
      m_shootOffButton, m_shooterReverseButton);

  private final Command m_findTarget = new SequentialCommandGroup(new FindTarget(m_limelight, m_drivetrain),
      new TurnTowardTarget(m_limelight, m_turret));

  private final Command m_runTurret = new RunTurret(m_shooterJoystick, m_turret);

  // Compressor for the arm
  private final Compressor m_compressor = new Compressor(Arm.Config.kPCMId);

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
    m_armDownButton.whenPressed(new ArmDown(m_arm));
    m_armUpButton.whenPressed(new ArmUp(m_arm));
    m_flywheelButton.whenPressed(m_controlShooter);
    m_rollerOnButton.whenPressed(new RollIntake(m_rollers));
    m_rollerOffButton.whenPressed(new StopIntake(m_rollers));
    m_hoodDownButton.whenPressed(new InstantCommand(() -> m_hood.hoodDown(), m_hood));
    m_hoodUpButton.whenPressed(new InstantCommand(() -> m_hood.hoodUp(), m_hood));
  }

  /**
   * Use this to get the command run on robot init.
   * 
   * @return the init comand
   */
  public Command getInitCommand() {
    SmartDashboard.putBoolean("Safe mode", false);
    SmartDashboard.putNumber("Target RPM", 4000.0);
    return new InstantCommand(() -> m_compressor.start(), m_arm);
  }

  /**
   * Use this to get the command run on robot disabled.
   * 
   * @return the disabled command
   */
  public Command getDisabledCommand() {
    return new InstantCommand(() -> m_compressor.stop(), m_arm);
  }

  /**
   * Use this to get the teleop command in the Robot class
   * 
   * @return the teleop command
   */
  public Command getTeleopCommand() {
    m_drivetrain.resetEncoder();
    m_drivetrain.setCoastMode();

    boolean safeMode = SmartDashboard.getBoolean("Safe mode", false);
    if (safeMode)
      m_drivetrain.setDefaultCommand(m_safeArcadeDrive);
    else
      m_drivetrain.setDefaultCommand(m_arcadeDrive);

    m_turret.resetEncoder();
    m_turret.setDefaultCommand(m_runTurret);

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

    return new TurnTowardTarget(m_limelight, m_turret);
  }
}
