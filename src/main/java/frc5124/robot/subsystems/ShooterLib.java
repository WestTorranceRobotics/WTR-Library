package frc5124.robot.subsystems;

public abstract class ShooterLib {
    //needs motor leader, follower, PID Controller

    public abstract double getVoltage();
    //in volts

    public abstract boolean atSpeed();

    public abstract void directPower(double power);

    public abstract void startShooter();

    public abstract void stopShooter();

    public abstract double getVelocity();
    //inches per second

    public abstract void startShooter(double rpm);

}
