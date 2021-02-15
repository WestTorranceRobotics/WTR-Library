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
    public WPI_TalonFX leftLeader;
    public WPI_TalonFX rightLeader;
    private WPI_TalonFX leftFollower;
    private WPI_TalonFX rightFollower;
    private AHRS gyro;
    private DifferentialDrive differentialDrive;
    private DifferentialDriveKinematics kinematics;
    private DifferentialDriveKinematicsConstraint trajectoryConstraint;
    private DifferentialDriveOdometry odometry;
    private PIDController angleController = new PIDController(0.00125,0.00005,0.000005);
    private ShuffleboardTab debuggingTab;
    
    private double INCHES_PER_TICK = (18.0f/28.0f) * (10.0f/64.0f) * 6.0f * Math.PI * (1.0f/2048.0f);
    public double TICKS_PER_INCH = 40 * (1.0/(Math.PI * 6.0) * 2048.0 * (64.0/10.0) * (28.0/18.0));

    public DriveTrainLib() {

        // leftLeader = new WPI_TalonFX(RobotMap.DriveTrainMap.leftLeaderCanID);
        // rightLeader = new WPI_TalonFX(RobotMap.DriveTrainMap.rightLeaderCanID);

        // leftFollower = new WPI_TalonFX(RobotMap.DriveTrainMap.leftFollowerCanID);
        // leftFollower.follow(leftLeader);
        // rightFollower = new WPI_TalonFX(RobotMap.DriveTrainMap.rightFollowerCanID);
        rightFollower.follow(rightLeader);

        rightLeader.setNeutralMode(NeutralMode.Brake);
        rightFollower.setNeutralMode(NeutralMode.Coast);
        leftLeader.setNeutralMode(NeutralMode.Brake);
        leftFollower.setNeutralMode(NeutralMode.Coast);

        leftLeader.setInverted(true);
        
        rightLeader.setInverted(false);

        leftFollower.setInverted(InvertType.FollowMaster);
        rightFollower.setInverted(InvertType.FollowMaster); 

        gyro = new AHRS(SPI.Port.kMXP);
        
        differentialDrive = new DifferentialDrive(leftLeader, rightLeader);
        differentialDrive.setSafetyEnabled(true);

        // kinematics = new DifferentialDriveKinematics(RobotMap.DriveTrainMap.kTrackwidth);
        // trajectoryConstraint = new DifferentialDriveKinematicsConstraint(kinematics, RobotMap.DriveTrainMap.kMaxVelocity);
        // gyro.reset();
        // gyro.zeroYaw();
        // last = gyro.getPitch();
        // odometry = new DifferentialDriveOdometry(getGyro());
        // resetOdometry();
    }

    abstract public void tankDrive(double left, double right);

    abstract public void arcadeDrive(double speed, double turn);

    abstract public void resetEncoders();

    abstract public void resetOdometry();

    abstract public Pose2d getLocation();

    abstract public double getLeftEncoderPosition();

    abstract public double getRightEncoderPosition();

    abstract public SpeedController getRightLeader();

    abstract public SpeedController getLeftLeader();

    abstract public Rotation2d getGyro();

    abstract public double getGryoDegree();

}