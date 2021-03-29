/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc5124.robot.subsystems.CanDriveTrain;

public class RunDistance extends CommandBase {
  /**
   * Creates a new resetGyro.
   */
  private CanDriveTrain m_DriveTrain;
  private boolean isDone = false;
  private double targetCounts;
  private double distanceToDrive;
  private double countsPerInch = 1082.0; 
  //number of encoder ticks per 1 revolution
  private double startingEncoderVal;
  private double x;
  private double power;
  private boolean goBackwards;
  private double backwardsSpeed = 0.75;

  public RunDistance(CanDriveTrain driveTrain, double x, double power, boolean goBackwards) {
    m_DriveTrain = driveTrain;
    addRequirements(m_DriveTrain);
    this.x = x;
    this.power = power;
    this.goBackwards = goBackwards;
  }

  @Override 
  public void initialize() {
    super.initialize();
    m_DriveTrain.resetEncoders();
    distanceToDrive = x; 
    targetCounts = countsPerInch * distanceToDrive;
    startingEncoderVal = m_DriveTrain.getLeftEncoderVal();
  }

  public void driveStraightToPoint(){  // after it figures out the angle, it should just drive straight
    if (goBackwards) {
      m_DriveTrain.tankDrive(-backwardsSpeed, -backwardsSpeed);  
    } else {
      m_DriveTrain.tankDrive(power, power);
    }
    //Option to go backwards in the RunDistance constructor
    if (Math.abs(m_DriveTrain.getLeftEncoderVal() - startingEncoderVal) >= Math.abs(targetCounts)){
      m_DriveTrain.tankDrive(0, 0);
      isDone = true;
    }
    //Once the robot has reached its calculated distance based on the number of encoder ticks, stop the robot and the command.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveStraightToPoint();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // SmartDashboard.putNumber("ended", 1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}