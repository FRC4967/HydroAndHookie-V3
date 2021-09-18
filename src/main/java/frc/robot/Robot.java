// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  SendableChooser<String> autoChooser;
  String autoSelected;

  BaseAutonomous baseAutonomous;

  final String threeBallAuto = "3 Ball Auto";
  final String doNothing = "No Movement Auto";
  final String visionAlign = "Line Robot Up";

  double rightMinSpeed = 0;
  double leftMinSpeed = 0;

  ControllerMap controller;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    controller = new ControllerMap();

    Indexer.getInstance();
    Intake.getInstance();
    Climber.getInstance();
    Shooter.getInstance();
    Limelight.getInstance();
    DriveTrain.getInstance();

    setUpAutoChoices();

    baseAutonomous = new AutoStayStill();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() 
  {
  DriveTrain.P = SmartDashboard.getNumber("P", 0);
  DriveTrain.D = SmartDashboard.getNumber("D", 0);

  // System.out.println(DriveTrain.P);
  // System.out.println(DriveTrain.D);

  DriveTrain.leftDrivePID.setP(DriveTrain.P);
  DriveTrain.rightDrivePID.setP(DriveTrain.P);
  DriveTrain.leftDrivePID.setD(DriveTrain.D);
  DriveTrain.rightDrivePID.setD(DriveTrain.D);
    
    DriveTrain.resetDriveEncoders();
    baseAutonomous.stop();

    autoSelected = (String) autoChooser.getSelected();

    baseAutonomous = new AutoStayStill();
    baseAutonomous.start();

    switch (autoSelected)
    {
      case threeBallAuto:
        baseAutonomous = new ThreeBallAuto();
        break;
      case doNothing:
        baseAutonomous = new AutoStayStill();
        break;
      case visionAlign:
        baseAutonomous = new AutoVisionAlign();
        break;
    }
    baseAutonomous.start();
  }

  private void setUpAutoChoices()
  {
    autoChooser = new SendableChooser<String>();
    autoChooser.setDefaultOption(threeBallAuto, threeBallAuto);
    autoChooser.addOption(doNothing, doNothing);
    autoChooser.addOption(visionAlign, visionAlign);
    SmartDashboard.putData("Autonoumous Chose:", autoChooser);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() 
  {

    
    DriveTrain.P = SmartDashboard.getNumber("P", 0);
    DriveTrain.D = SmartDashboard.getNumber("D", 0);

    // System.out.println(DriveTrain.P);
    // System.out.println(DriveTrain.D);

    DriveTrain.leftDrivePID.setP(DriveTrain.P);
    DriveTrain.rightDrivePID.setP(DriveTrain.P);
    DriveTrain.leftDrivePID.setD(DriveTrain.D);
    DriveTrain.rightDrivePID.setD(DriveTrain.D);

    SmartDashboard.putNumber("Left encoder inches", DriveTrain.ticksToInches(DriveTrain.getLeftDriveEncoder()));
    SmartDashboard.putNumber("Right encoder inches", DriveTrain.ticksToInches(DriveTrain.getRightDriveEncoder()));
    if (baseAutonomous.isRunning())
    {
      baseAutonomous.periodic();
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit()
  {
    SmartDashboard.putNumber("P", DriveTrain.P);
    SmartDashboard.putNumber("D", DriveTrain.D);
    DriveTrain.teleopDrive();
    DriveTrain.resetDriveEncoders();
    baseAutonomous.stop();
    Intake.stop();
    Indexer.indexerStop();
    Indexer.beltStop();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() 
  {
    // drive base control

    DriveTrain.P = SmartDashboard.getNumber("P", 0);
    DriveTrain.D = SmartDashboard.getNumber("D", 0);

    SmartDashboard.putNumber("Left encoder inches", DriveTrain.ticksToInches(DriveTrain.getLeftDriveEncoder()));
    SmartDashboard.putNumber("Right encoder inches", DriveTrain.ticksToInches(DriveTrain.getRightDriveEncoder()));

    leftMinSpeed = controller.leftJoystick.getRawAxis(1);
    rightMinSpeed = controller.rightJoystick.getRawAxis(1);
    controller.periodic();

    SmartDashboard.putNumber("left side", leftMinSpeed);
    SmartDashboard.putNumber("right side", rightMinSpeed);

    if (Math.abs(leftMinSpeed) > 0.1 || Math.abs(rightMinSpeed) > 0.1)
    {
      // makes sure we don't drive in our deadzone
      if (Math.abs(leftMinSpeed) < 0.1)
      {
        leftMinSpeed = 0;
      }
      if (Math.abs(rightMinSpeed) < 0.1)
      {
        rightMinSpeed = 0;
      }
      // make sure that we are never driving at 100% and that max we can go is 90% on the left side
      if (Math.abs(leftMinSpeed) > 0.1)
      {
        if (leftMinSpeed > 0.1)
        {
          leftMinSpeed = leftMinSpeed - 0.1;
        }
        else if (leftMinSpeed < -0.1)
        {
          leftMinSpeed = leftMinSpeed + 0.1;
        }
      }
      // make sure that we are never driving at 100% and that max we can go is 90% on the right side
      if (Math.abs(rightMinSpeed) > 0.1)
      {
        if (rightMinSpeed > 0.1)
        {
          rightMinSpeed = rightMinSpeed - 0.1;
        }
        else if (rightMinSpeed < -0.1)
        {
          rightMinSpeed = rightMinSpeed + 0.1;
        }
      }
      // actually drives after all of the safety nets in place
      DriveTrain.driveTeleop(leftMinSpeed, rightMinSpeed);
     }

    // subsystem control
    // System.out.println("aidan");
    if (controller.intakeIn())
    {
      // System.out.println("intake");
      Intake.start();
    }
    else if (controller.intakeOut())
    {
      Intake.reverse();
    }
    else
    {
      Intake.stop();
    }
    if (controller.indexForward())
    {
      Indexer.beltForward();
      Indexer.indexerForward();
    }
    else if (controller.indexBackward())
    {
      Indexer.beltBackward();
      Indexer.indexerBackward();
    }
    else
    {
      Indexer.beltStop();
      Indexer.indexerStop();
    }
    if (controller.startAndStopShooting())
    {
      Shooter.startAndStopShooting();
    }
    if (controller.constantShooting())
    {
      Shooter.constantShooting();
    }
    if (controller.engageClimberFailSafe())
    {
      Climber.extendFishPole();
    }
    else if (controller.fishingPoleDown())
    {
      Climber.retractFishPole();
    }
    else
    {
      Climber.stopFishPole();
    }
    if (controller.reelInClimber())
    {
      Climber.climberDescend();
    }
    else if (controller.reelOutClimber())
    {
      Climber.climberElevate();
    }
    else if(controller.rightJoystick.getRawButtonPressed(8)){

      Climber.climberStop();
    }
  }
  

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    Limelight.turnLedsOff();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() 
  {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
