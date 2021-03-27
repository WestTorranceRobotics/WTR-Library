/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.turretcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc5124.robot.subsystems.Turret;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// NEEDS FURTHER TESTING TO NOT MAKE DEAD METHOD

public class RotateTurret extends CommandBase {
  private Turret turret;
  private Boolean clockwise = false;
  private double lastDegrees = 0;

  public RotateTurret(Turret subsystem, Boolean clockwise) {
    turret = subsystem;
    addRequirements(turret);
    this.clockwise = clockwise;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // if (clockwise && !turret.isAutomatic()) {
    //   turret.directPower(-RobotMap.TurretMap.turretSpeed);
    // } else if (!clockwise && !turret.isAutomatic()) {
    //   turret.directPower(RobotMap.TurretMap.turretSpeed);
    // } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.directPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}




// public class setTurretDegrees extends CommandBase {
//   private Turret turret;
//   private double degrees;
//   /**
//    * Creates a new setTurretDegrees.
//    */
//   public setTurretDegrees(Turret subsystem, double degrees) {
//     turret = subsystem;
//     this.degrees = degrees;
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     turret.setTurretDegrees(degrees);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }