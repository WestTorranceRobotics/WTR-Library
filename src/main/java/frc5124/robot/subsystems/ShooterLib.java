package frc5124.robot.subsystems;

public abstract class ShooterLib {
    
    public abstract double getVoltage();

    public abstract boolean atSpeed();

    public abstract void directPower(double power);

    public abstract void startShooter();

    public abstract void stopShooter();

    public abstract double getVelocity();

    public abstract void startShooter(double rpm);

}
