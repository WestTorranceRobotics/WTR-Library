package frc5124.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;


public abstract class IntakeLib implements Subsystem {

    /*A robot intake. The intakes use */

  @Override
  public void periodic() {
  }

  public abstract void setIntakePower(double power);

  public abstract void flushOut();

  public abstract double getOutput();
}