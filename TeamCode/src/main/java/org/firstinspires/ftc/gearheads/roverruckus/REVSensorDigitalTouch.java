/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.gearheads.roverruckus;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import edu.boscotech.gearheads.gearheader.FTCModularizableSystems;

/*
 * This is an example LinearOpMode that shows how to use
 * a REV Robotics Touch Sensor.
 *
 * It assumes that the touch sensor is configured with a name of "digitalTouch".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 */
@TeleOp(name = "Sensor: Digital touch using Threading", group = "Sensor")
@Disabled

public class REVSensorDigitalTouch extends OpMode{
    DigitalChannel digitalTouch;  // Hardware Device Object
    DcMotor motor;
    RevTouchSensor revTouchSensor;
    TouchSensorMotor touchMotor = new TouchSensorMotor(){
        @Override
        public void run() {
            super.run();
            while(!revTouchSensor.isPressed());
            motor.setPower(0.6);
        }
    };


    @Override
    public void init() {
        touchMotor.initHardware(hardwareMap);
    }

    @Override
    public void start() {
        touchMotor.start(); //creates new thread and calls run();
    }

    @Override
    public void loop() { //check if threads are done to proceed to next threads


    }

    @Override
    public void stop() {
        super.stop();
        touchMotor.interrupt();
    }
}

class TouchSensorMotor extends Thread implements FTCModularizableSystems {
    DcMotor motor;
    RevTouchSensor revTouchSensor;
    TouchSensorMotor(){

    }
    @Override
    public void initHardware(HardwareMap ahwMap) {
        motor = ahwMap.get(DcMotor.class, "EH1motor0");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0);
        revTouchSensor = ahwMap.get(RevTouchSensor.class, "sensor_digital");
    }
}

/*



public class REVSensorDigitalTouch extends LinearOpMode {
    /**
     * The REV Robotics Touch Sensor
     * is treated as a digital channel.  It is HIGH if the button is unpressed.
     * It pulls LOW if the button is pressed.
     *
     * Also, when you connect a REV Robotics Touch Sensor to the digital I/O port on the
     * Expansion Hub using a 4-wire JST cable, the second pin gets connected to the Touch Sensor.
     * The lower (first) pin stays unconnected.*


    DigitalChannel digitalTouch;  // Hardware Device Object
    DcMotor motor;
    RevTouchSensor revTouchSensor;


    @Override
    public void internalPreInit() {
        super.internalPreInit();
        revTouchSensor = hardwareMap.get(RevTouchSensor.class, "sensor_digital");
        motor = hardwareMap.get(DcMotor.class, "EH1motor0");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0.6);
        while(!revTouchSensor.isPressed());
        motor.setPower(0);
    }

    @Override
    public void runOpMode() {

        // get a reference to our digitalTouch object.
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");



        // set the digital channel to input.
        //digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.

            if (revTouchSensor.isPressed() == true) {
                telemetry.addData("Digital Touch", "Is Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is not pressed");
            }



            telemetry.update();
        }
    }
}
*/





