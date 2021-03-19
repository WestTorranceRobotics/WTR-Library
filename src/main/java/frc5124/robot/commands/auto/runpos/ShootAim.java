/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.auto.runpos;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc5124.robot.commands.auto.ShootThreeBalls;
import frc5124.robot.commands.auto.ShootThreeByFF;
import frc5124.robot.commands.shootercommands.ShootTuner;
import frc5124.robot.commands.turret.TurretTargetByPIDPerpetually;
import frc5124.robot.subsystems.Turret;
import frc5124.robot.subsystems.Loader;
import frc5124.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootAim extends ParallelDeadlineGroup {
  /**
   * Creates a new shootAim.
   */
  public ShootAim(Shooter shooter, Loader loader, Turret turret) {
    // Add your commands in the super() call.  Add the deadline first.
    super(new ShootThreeByFF(shooter, loader, 4350, 2), new TurretTargetByPIDPerpetually(turret));
    //TurretTargetByPIDPerpetually is gone, replace with TurretTargetbyPID eventually (or never)?
  }
}