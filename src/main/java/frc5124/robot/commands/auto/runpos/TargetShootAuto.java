/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.auto.runpos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc5124.robot.subsystems.CanDriveTrain;
import frc5124.robot.subsystems.Shooter;
import frc5124.robot.subsystems.Loader;
import frc5124.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TargetShootAuto extends SequentialCommandGroup {
  /**
   * Creates a new TargetShootAuto.
   */

  public TargetShootAuto(Shooter shooter, Loader loader, Turret turret, CanDriveTrain driveTrain) {
    // super(new Turn180(turret), new ShootAim(shooter, loader, turret), new DriveForTime(driveTrain, 2));
    // Shoot aim super(new ShootThreeByFF(shooter, loader, 4350, 2), new TurretTargetByPID(turret, true));
  }
}