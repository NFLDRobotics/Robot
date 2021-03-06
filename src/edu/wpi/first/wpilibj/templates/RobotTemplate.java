/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class RobotTemplate extends SimpleRobot {
    /*
     * This function is called once each time the robot enters autonomous mode.
     */
    RobotDrive chassis = new RobotDrive(1, 2);
    Joystick rightStick = new Joystick(1);
    Joystick leftStick = new Joystick(2);
    Talon launchMotor = new Talon(3);
    Compressor myCompressor = new Compressor(1,4);
    //DigitalInput pressure = new DigitalInput(1);
    //Relay myCompressor = new Relay(4);
   
    Solenoid launchActuatorIn = new Solenoid (1);
    Solenoid launchActuatorOut = new Solenoid(2);
    Solenoid frisbeeWaitIn = new Solenoid(3);
    Solenoid frisbeeWaitOut = new Solenoid(4);
    Solenoid climberActuatorIn = new Solenoid (5);
    Solenoid climberActuatorOut = new Solenoid (6);
    
    /* Sensors */
    //AnalogChannel rangeFinder = new AnalogChannel(5);
    
    
    /* IO */
    
    
    //boolean TankDrive=true; // for tankdrive
    /*
    double distance;
    final double PI = 3.14159;
    final double DIAMETER = 1;
    final double CIRCUMFERENCE = PI*DIAMETER;
    final double SPEEDCONST = 1;
    */
    public void robotInit()
    {
        myCompressor.start();
    }
    
    public void autonomous() {
        myCompressor.start();
        
        System.out.println("Autonomous enabled");
        chassis.setSafetyEnabled(false);
        launch();
        Timer.delay(1.0);
        launch();
        Timer.delay(1.0);
        launch();
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    
    public void operatorControl() {
        myCompressor.start();
        
        System.out.println("Teleop enabled");
        chassis.setSafetyEnabled(true);
        //myCompressor.set(Relay.Value.kOn);

        while (isOperatorControl() && isEnabled()) {
            /*
             if (!pressure.get())
            {
               myCompressor.set(Relay.Value.kOn);
            }
            else
            {
                myCompressor.set(Relay.Value.kOff);
            }
            */
            if (leftStick.getRawButton(1))
            {
                System.out.println("Pressure: "+myCompressor.getPressureSwitchValue());
            }
            
         
           if (rightStick.getRawButton(1))//Motor Spinup
            {
               // launch();
               launchMotor.setSafetyEnabled(false);
               System.out.println(rightStick.getMagnitude());
               
               launchMotor.set(1);
               launchMotor.setSafetyEnabled(true);
            }
            else
            {
                launchMotor.setSafetyEnabled(false);
                launchMotor.set(0);
                launchMotor.setSafetyEnabled(true);
            }    
            
            if (leftStick.getRawButton(1))//Launch Arm
            {
                launchActuatorIn.set(true);
                launchActuatorOut.set(false);
            }
             else 
            {
                launchActuatorOut.set(true);
                launchActuatorIn.set(false);
            }
            
            if (rightStick.getRawButton(2))//Obsolete
            {
                System.out.println("<--Range Get-->");
                getRange();
                Timer.delay(.1);
            }
            
            if (rightStick.getRawButton(4))
            {
                launchActuatorOut.set(true);
                launchActuatorIn.set(false);
                
            }    
            if (rightStick.getRawButton(3))//Climber Arms
            {
                climberActuatorIn.set(true);
                climberActuatorOut.set(false);
            }   
          
            else 
            {
                climberActuatorIn.set(false);
                climberActuatorOut.set(true);
            }
            
            
            
           /* if (leftStick.getRawButton(4))
            {
                frisbeeWaitIn.set(true);
                frisbeeWaitOut.set(false);
            }
            if (leftStick.getRawButton(5))
            {
                frisbeeWaitIn.set(false);
                frisbeeWaitOut.set(true);
                
            } */
            
           /* if (leftStick.getRawButton(4))
            {
                frisbeeWaitIn.set(true);
                frisbeeWaitOut.set(false);
                
            }
            else
            {
                 frisbeeWaitIn.set(false);
                 frisbeeWaitOut.set(true);
            } */
             
            if (leftStick.getRawButton(3))//See function "Load"
            
            {
                load();
            }
            
            /*if (rightStick.getRawButton(5))
            {
                frisbeeWaitOut.set(true);
                frisbeeWaitIn.set(false);
                Timer.delay(1);
                frisbeeWaitIn.set(false);
                frisbeeWaitOut.set(true);
            }  */  
             
            
            /*if (rightStick.getRawButton(4))
            {
                launchActuator.set(true);
                Timer.delay(0);
                launchActuator.set(false);
               
            }
            if (leftStick.getRawButton(2)&&TankDrive)
            {
                TankDrive=false;
            }
            else if (leftStick.getRawButton(2))
            {
                TankDrive=true;
            }
            if (TankDrive)
            {*/
                chassis.tankDrive(leftStick, rightStick);//Simple drive system
            /*}
            else
            {
                chassis.arcadeDrive(leftStick);
            }*/
            Timer.delay(0.01);
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
    
   public double getRange()
           
    {
        
        //System.out.println("/t"+rangeFinder.getVoltage()+"MM");
        //return distance=rangeFinder.getVoltage();
        return 0.0;
        
    }
    
    public double calcSpeed()
    {
        return 1; //distance/(CIRCUMFERENCE)*SPEEDCONST;
    }
   
    public void launch()//Auto frisbee launch mode
    {
        launchActuatorOut.set(true);
        launchActuatorIn.set(false);
        launchMotor.setSafetyEnabled(false);
        launchMotor.set(1);//calcSpeed());
        Timer.delay(2.0);
        //Timer.delay(1.0);
        launchActuatorOut.set(false);
        launchActuatorIn.set(true);
        //launchMotor.set(0);
        Timer.delay(0.5);
        launchActuatorIn.set(false);
        launchActuatorOut.set(true);
        launchMotor.setSafetyEnabled(true);
        Timer.delay(0.5);
        load();
    }  
     
    public void load()//Drops frisbe into chamber
    {
        frisbeeWaitIn.set(true);
        frisbeeWaitOut.set(false);
        Timer.delay(.5);
        frisbeeWaitIn.set(false);
        frisbeeWaitOut.set(true);
        Timer.delay(0.2); 
    }
    
}
