package frc5124.robot.subsystems;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc5124.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public abstract class DriveTrainLib extends SubsystemBase {
    

    public AHRS gyro;
    public DifferentialDrive differentialDrive;
    public DifferentialDriveKinematics kinematics;
    public DifferentialDriveKinematicsConstraint trajectoryConstraint;
    public DifferentialDriveOdometry odometry;

     /*
    
        When getting values from motors, like encoder ticks, we need to convert those ticks into actual measurement. We can then use 
        these measurements to determine the robot position on the field using odometory. Odometry is very useful as it can allow
        to know where the robot is on the field at all times and allow us to make fairly good estimates as where we want the robot
        travel to.
    
    */

    public double INCHES_PER_TICK; //= (18.0f/28.0f) * (10.0f/64.0f) * 6.0f * Math.PI * (1.0f/2048.0f);
    public double TICKS_PER_INCH; // = 40 * (1.0/(Math.PI * 6.0) * 2048.0 * (64.0/10.0) * (28.0/18.0));

    /**
   * Tank drive is the main driving method our team uses for driving the robot. Tank drive is part of the WPI Differential Drive
   * and more information on tank drive can be found there. For tank drive, we are able to use it as we have 2 joysticks, each 
   * controlling the power for the left motors and right motors.
   * 
   * @param left The power value you are applying to the left motor/speedcontroller. Values range from [-1.0,1.0]. The direction
   * can depend on how you set the motors up however, postitive is usally forward.
   * 
   * @param right The power value you are applying to the right motor/speedcontroller. Values range from [-1.0,1.0]. The direction
   * can depend on how you set the motors up however, postitive is usally forward.
   * 
   */

    abstract public void tankDrive(double left, double right);

    /**
   * Arcade drive is the a driving method that can be used to drive the robot. Arcade drive is part of the WPI Differential Drive
   * and more information on acrade drive can be found there. For acrade drive, we rarely use it and prefer to use tank drive 
   * instead. If we wanted to use it, there are a number of possible ways to do it but we stick with tank drive.
   * 
   * @param speed The robot's speed value applied to both the motors. Values range from [-1.0,1.0]. Positive is forward. 
   * 
   * @param turn The robot's turn rate which rotates the robot around it's z axis. Values range from [-1.0, 1.0]. Positive is 
   * clockwise.
   * 
   */

    abstract public void arcadeDrive(double speed, double turn);

    /**
   * ResetEncoders is a method which is used to reset the ecoder values for the motors. Reseting econder values is important
   * as we can get incorrect readings on the distance traveled if the motor ecoder values have not been reset properly. Knowing
   * the encoder values is important which is why reseting is important because it allows us to reset the position of our encoders which 
   * makes traveling a certain distance easier.
   * 
   */

    abstract public void resetEncoders();

    /**
   * ResetOdometry is a method which is used to reset the odometry for our robot. This allows us to set the robot's current position
   * at (0,0) with rotation also being at 0 rad. This allows us to reset our position and make that point where the robot is currently
   * the origin. 
   * 
   */

    abstract public void resetOdometry();
    
     /**
   * getLocation returns the location of the robot in reference to it's odometry. this allows us to track the position of the robot on the field by using
   * odometry. It's helpful when we need to move to a certain location or want to know where our robot is.
   * 
   */

    abstract public Pose2d getLocation();
    
     /**
   * getLeftEncoderPosition() and getRightEncoderPosition() returns the left encoder ticks and right encoder ticks respectively 
   * These encoders ticks helps us when caculating how far the robot has traveled. By using encoder positions, we are able to determine 
   * the distance a robot has traveled by using converting ticks to revolutions to distance traveled.
   * 
   */

    abstract public double getLeftEncoderPosition();

    abstract public double getRightEncoderPosition();

    abstract public SpeedController getRightLeader();

    abstract public SpeedController getLeftLeader();

    abstract public Rotation2d getGyro();

    abstract public double getGryoDegree();

}
