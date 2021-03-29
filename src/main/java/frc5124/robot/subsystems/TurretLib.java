package frc5124.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TurretLib extends SubsystemBase {
    //needs PID Controller
    
    public abstract void setTurretDegrees(double degrees);

    public abstract void turretLimitSet();

    public abstract void disableTurretLimit();

    public abstract void resetTurretDegrees();

    public abstract void setHome(boolean setHome);

    public abstract boolean isHome();

    public abstract double getVoltage();
    //in volts

    public abstract double getCurrent();

    public abstract void directPower(double power);

    public abstract double getRotations();
    //rpm

    public abstract double getDegrees();

    public abstract int getEncoderCountsPerRevolution();

    public abstract SpeedController getMotor();

}
