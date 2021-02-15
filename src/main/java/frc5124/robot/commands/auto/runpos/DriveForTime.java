/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.auto.runpos;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc5124.robot.subsystems.CanDriveTrain;

public class DriveForTime extends WaitCommand {
  CanDriveTrain driveTrain;
  /**
   * Creates a new DriveForTime.
   */
  public DriveForTime(CanDriveTrain subsystem, double time) {
    super(time);
    driveTrain = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    super.execute();
    driveTrain.tankDrive(.4, .4);
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    driveTrain.tankDrive(0, 0);
  }
}