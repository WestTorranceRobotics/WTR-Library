/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc5124.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc5124.robot.subsystems.Loader;
import frc5124.robot.subsystems.Shooter;


public class ShootTuner extends CommandBase {
  private Shooter m_shooter;
  private Loader m_loader;
  enum shootModes {
    LINE,
    TRENCH,
    START,
    STOP
  }
  private shootModes shooterMode;
  /**
   * Creates a new setShootVelocity.
   */
  public ShootTuner (Shooter shooter, shootModes mode, Loader loader) {
    m_shooter = shooter;
    m_loader = loader;
    addRequirements(m_shooter);
    this.shooterMode = mode;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_shooter.startShooter(RobotMap.ShooterMap.lineShootRPM);
  } 

  private void executeMode(shootModes mode) {
    switch(mode){
      case LINE:
        // if (m_shooter.atSpeed()) {
        //   m_shooter.currentWatch(RobotMap.ShooterMap.lineShootRPM);
        // }
        // if (m_shooter.getVelocity() >= RobotMap.ShooterMap.lineShootRPM + 5 && m_loader.getAppliedOutput() == 0) {
        //   m_loader.runBelt(.75);
        //   m_shooter.atSpeed(true);
        // } 
        break;
      case TRENCH:
              // if (m_shooter.atSpeed()) {
        //   m_shooter.currentWatch(RobotMap.ShooterMap.trenchShootRPM);
        //   }
        //   if (m_shooter.getVelocity() >= RobotMap.ShooterMap.trenchShootRPM-20 && m_loader.getAppliedOutput() == 0) {
        //     m_loader.runBelt();
        //     m_shooter.atSpeed(true);
        //   } 
        break;
      case START:
        m_shooter.updatePID();
        break;
      default:
        m_shooter.directPower(0);
        break;
    }
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    executeMode(shooterMode);
  
  }

  // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }

  private void endMode(shootModes mode) {
    switch(mode){
      case STOP:
        m_shooter.directPower(0);
        break;
      case START:
        m_shooter.stopShooter();
        break;
      default:
        m_shooter.stopShooter();
        m_loader.stopBelt();
        m_shooter.atSpeed(false);
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    endMode(shooterMode);
  }
  
}