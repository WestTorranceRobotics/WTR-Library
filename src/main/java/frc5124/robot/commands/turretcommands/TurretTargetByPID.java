/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.turretcommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc5124.robot.subsystems.Turret;

public class TurretTargetByPID extends CommandBase {
  private Turret subsystem;
  private boolean enableContinuousTargeting;
  /**
   * Creates a new TurretTargetByPID.
   */
  public TurretTargetByPID(Turret subsystem, boolean enableContinuousTargeting) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
    this.enableContinuousTargeting = enableContinuousTargeting;
    //setting to enable continuous targeting
  }


  // Called when the command is initially scheduled.
  public void initialize() {
    if (enableContinuousTargeting == false) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("trackNow").setBoolean(true);
      double target = subsystem.getDegrees() - 
          NetworkTableInstance.getDefault().getTable("limelight")
          .getEntry("tx").getDouble(0);
      subsystem.setTurretDegrees(target);
    }
    //If enableContinuousTarget is turned off when initializing command, will find target once and move.
    //Once target is found, command will exit.
    else {
      NetworkTableInstance.getDefault().getTable("rpi").getEntry("aimbot").setDouble(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0.0);
      subsystem.enableTurretPID();
      subsystem.setCoast();
      subsystem.isAutomatic(true);
    }
    //Having a True enableContinuousTarget will enable the 'aimbot'. Will automatically find target and move continuously.
    //Once target is found, command will not exit.
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (enableContinuousTargeting == false) return;
    //If it is not enabled, return nothing. 

    double target = subsystem.getDegrees() - 
    NetworkTableInstance.getDefault().getTable("limelight")
    .getEntry("tx").getDouble(0);
    subsystem.setTurretDegrees(target);
  // SmartDashboard.putNumber("Deg", subsystem.getDegrees());
  }


  //end() will run when isFinished returns True
  @Override
  public void end(boolean interrupted) {
    if (enableContinuousTargeting == false) {
      new Thread(() -> {
        try {
          Thread.sleep(500);
        } catch (InterruptedException ex) {
          return;
        }
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("trackNow").setBoolean(false);
      }).start();
    }
    //If enableContinuousTarget is false, will turn off Turret Tracking.

    else {
      subsystem.isAutomatic(false);
      NetworkTableInstance.getDefault().getTable("rpi").getEntry("aimbot").setDouble(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1.0);
      subsystem.setBrake();
    }
    //If enableContinuousTarget is true, will turn off Turret 'aimbot'
  }


  @Override
  public boolean isFinished() {
    return !enableContinuousTargeting;
    //Returns the opposite boolean supplied. If enableContinuousTarget was false by default, will make end() run.
    //If enableContinuousTarget true by default, will not make end() run
  }
}