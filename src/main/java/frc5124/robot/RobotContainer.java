/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.HashMap;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.GyroBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc5124.robot.commands.*;

import frc5124.robot.commands.auto.ChangeCamera;

import frc5124.robot.commands.auto.ShootThreeBalls;
import frc5124.robot.commands.auto.RunDistanceForward;
import frc5124.robot.commands.auto.runpos.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc5124.robot.commands.drivetraincommands.*;
import frc5124.robot.commands.intakecommands.*;
import frc5124.robot.commands.loadercommands.*;
import frc5124.robot.commands.shootercommands.*;
import frc5124.robot.commands.turretcommands.*;
import frc5124.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
  * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  
  private Intake intake;
  private CanDriveTrain driveTrain;
  private Shooter shooter; 
  private Turret turret;
  private Loader loader;

  public static final Joystick driverLeft = new Joystick(0);
  public static final Joystick driverRight = new Joystick(1);
  public XboxController operator = new XboxController(2);
  
  public JoystickButton operatorA = new JoystickButton(operator, 2);
  public JoystickButton operatorB = new JoystickButton(operator, 3);
  public JoystickButton operatorX = new JoystickButton(operator, 1);
  public JoystickButton operatorY = new JoystickButton(operator, 4);
  public JoystickButton operatorLB = new JoystickButton(operator, 5);
  public JoystickButton operatorRB = new JoystickButton(operator, 6);
  public JoystickButton operatorLT = new JoystickButton(operator, 7);
  public JoystickButton operatorRT = new JoystickButton(operator, 8);
  public JoystickButton operatorBack = new JoystickButton(operator, 9);
  public JoystickButton operatorStart = new JoystickButton(operator,10);
  public JoystickButton operatorTest = new JoystickButton(operator, 9);
  public JoystickButton operatorStickLeft = new JoystickButton(operator, 11);
  public JoystickButton operatorStickRight = new JoystickButton(operator, 12);

  public JoystickButton driverRightTrigger = new JoystickButton(driverRight, 1);
  public JoystickButton driverRightThumb  = new JoystickButton(driverRight, 2);

  public POVButton operatorUp = new POVButton(operator, 0);
  public POVButton operatorDown = new POVButton(operator, 180);
  public POVButton operatorRight = new POVButton(operator, 90);
  public POVButton operatorLeft = new POVButton(operator, 270);
  
  private ShuffleboardTab display;
  private Supplier<String> autoNameSupplier;
  private HashMap<String, Command> autonomies = new HashMap<>();

  public RobotContainer() {
    configureSubsystems();
    configureButtonBindings();
    configureShuffleboard();
    configureDefaultCommands();
  }

  private void configureSubsystems() {
    intake = new Intake();
    loader = new Loader();
    driveTrain = new CanDriveTrain();
    shooter = new Shooter();
    turret = new Turret();
  }

  private void configureButtonBindings(){
    operatorStart.whileHeld(new SetIntakePower(intake, -.6));
    operatorA.whenPressed(new ToggleIntakePivot(intake));
    // operatorB.toggleWhenPressed(new TurretTargetByPIDPerpetually(turret));
    operatorRight.whileHeld(new RotateTurret(turret, false));
    operatorLeft.whileHeld(new RotateTurret(turret, true));
    operatorRB.toggleWhenPressed(new RPMbyFF(shooter, loader, 4400)); //line distance
    operatorLB.toggleWhenPressed(new RPMbyFF(shooter, loader, 4950)); //trench distance
    operatorY.whileHeld(new RunLoader(loader, 1)); //power placeholder

    driverRightTrigger.whenPressed(new ChangeCamera(
      () -> ChangeCamera.lastSelection == ChangeCamera.INTAKE_CAM ? ChangeCamera.CLIMB_CAM : ChangeCamera.INTAKE_CAM)
    );
    driverRightThumb.whenPressed(new ChangeCamera(ChangeCamera.LIMELIGHT));
  }

  private void configureDefaultCommands(){
    driveTrain.setDefaultCommand(new JoystickTankDrive(driverLeft, driverRight, driveTrain));

    // Command zeroTurret = new TurretZeroAndTurn(turret);
    // autonomies.put("Trench Zero Turret", zeroTurret);
    // autonomies.put("Middle Zero Turret", zeroTurret);
    // autonomies.put("Opposing Trench Zero Turret", zeroTurret);
  }

  private void configureShuffleboard() {
    display = Shuffleboard.getTab("Driving Display");
    Shuffleboard.selectTab(display.getTitle());

    NetworkTableEntry pipeEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");

    display.addNumber("Balls Intaked", loader::getBallsIntaked)
    .withPosition(3, 1).withSize(1, 1);
    display.addBoolean("Limelight On?",() -> (int) pipeEntry.getDouble(-1) == 0)
    .withPosition(4, 1).withSize(1, 1).withWidget(BuiltInWidgets.kBooleanBox);
    display.addBoolean("Intake Running?", () -> Math.abs(intake.getOutput()) > 0.0001)
    .withPosition(3, 2).withSize(1, 1).withWidget(BuiltInWidgets.kBooleanBox);
    display.addBoolean("Shooter On?", shooter::active)
    .withPosition(4, 2).withSize(1, 1).withWidget(BuiltInWidgets.kBooleanBox);

    SendableChooser<String> selectionsA = new SendableChooser<>();
    selectionsA.addOption("Trench", "Trench");
    selectionsA.addOption("Middle", "Middle");
    selectionsA.addOption("Opposing Trench", "Opposing Trench");
    display.add("Start Position Selector", selectionsA)
    .withPosition(5, 1).withSize(2, 1).withWidget(BuiltInWidgets.kComboBoxChooser);
    
    SendableChooser<String> selectionsB = new SendableChooser<>();
    selectionsB.addOption("Primary", "Primary");
    selectionsB.addOption("Secondary", "Secondary");
    selectionsB.addOption("Zero Turret", "Zero Turret");
    display.add("Mode Selector", selectionsB)
    .withPosition(5, 2).withSize(2, 1).withWidget(BuiltInWidgets.kComboBoxChooser);

    autoNameSupplier = () -> selectionsA.getSelected() + " " + selectionsB.getSelected();

    LiveWindow.disableAllTelemetry();
    SmartDashboard.delete("limelight_Interface");
    SmartDashboard.delete("limelight_Stream");
    SmartDashboard.delete("limelight_PipelineName");
    SmartDashboard.delete("Heartbeat");
  }

  public static GyroBase shuffleboardGyro(DoubleSupplier d) {
    return new GyroBase(){
      @Override public void close() {}
      @Override public void reset() {}
      @Override public double getRate() {return 0;}
      @Override public double getAngle() {return d.getAsDouble();}
      @Override public void calibrate() {}
    };
  }

  /**
   * Code to run when starting teleop mode.
   */
  public void teleopInit() {
  }

  /**
   * Code to run when starting autonomous mode.
   */
  public void autonomousInit() {
  }

  /**
   * Code to run when disabling the robot.
   */
  public void disabledInit() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonomies.get(autoNameSupplier.get());
  }
}
