package frc5124.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class LoaderLib extends SubsystemBase {
    
    public abstract void setPower(double power);

    public abstract void runLoader(double power);

    public abstract void stopLoader();
    
    public abstract boolean detectObject();
    //probably needs to use camera

    public abstract int objectIntaked();

    public abstract double getVoltage();
    //in volts, needs motion sensor

    public abstract double returnRotations();
    //in rpm

    public abstract int getObjectsIntaked();

}
