/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.turretcommands;

import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc5124.robot.subsystems.Turret;

public class returnTurretToStart extends CommandBase {
  private Turret turret;
  private CANPIDController turretPID;
  private float turretP;
  private float turretI;
  private float turretD;
  private boolean isDone = false;
  private float turretSpeed;

  /**
   * Creates a new returnTurretToStart.
   */
  public returnTurretToStart(Turret subsystem, float turretP, float turretI, float turretD, float turretSpeed) {
    turret = subsystem;
    addRequirements(turret);
    
    //Creates PID to use
    this.turretP = turretP;
    //p = 0.0004
    this.turretI = turretI;
    //i = 0
    this.turretD = turretD;
    //d = 0

    this.turretSpeed = turretSpeed;
    //turretSpeed = 0.3    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turretPID = turret.getMotor().getPIDController();
    turretPID.setP(turretP);
    turretPID.setI(turretI);
    turretPID.setD(turretD);
    //Sets the PID constants for the turret based on robot weight
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(turret.getEncoderCountsPerRevolution() < 0){
      turret.getMotor().set(turretSpeed);
      //turns the turret motor in a positive direction (forwards)
    }
    if(turret.getEncoderCountsPerRevolution() > 0){
      turret.getMotor().set(-turretSpeed);
      //turns the turret motor in a negative direction (backwards)
    }
    else{
      isDone = true;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //sets turret motor and encoder to 0
    turret.getMotor().set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}