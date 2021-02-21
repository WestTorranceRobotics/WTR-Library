package frc5124.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class LoaderLib extends SubsystemBase {
    
    public abstract void setPower(double power);

    public abstract void runLoader(double power);

    public abstract void stopLoader(double power);
    
    public abstract boolean detectObject();

    public abstract int objectIntaked();

    public abstract double getVoltage();

    public abstract double returnRotations();

    public abstract int getObjectsIntaked();

}
