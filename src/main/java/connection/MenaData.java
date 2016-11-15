package connection;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that abstracts the access to the legacy system
 */
public class MenaData {
    /**
     * Robot that allows to input keystrokes programmatically
     */
    private Robot robot;
    /**
     * DOSBox process
     */
    private Process dosbox;

    /**
     * Class constructor
     * Starts DOSBox and start the Robot interface
     */
    public MenaData() {
        this("");
    }

    /**
     * Class constructor
     * Starts DOSBox and start the Robot interface. Executes the given command on emulator start
     */
    public MenaData(String autoExecute) {
        // Create and run process and Robot
        ProcessBuilder builder = new ProcessBuilder(Const.EXECUTABLE_NAME);
        try {
            this.dosbox = builder.start();
        } catch (IOException e) {
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // NOBODY CARES ABOUT YOUR EXCEPTIONS
        }
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            System.out.println("Interface Robot impossible to start. Shutting down.");
            System.exit(1);
        }
        // Autoexecute command, if given
        if (autoExecute != null && autoExecute.length() > 0) {
            RobotType.typeString(autoExecute, this.robot);
        }
        // Wait until the program is loaded and running
        try {
            Thread.sleep(2750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current number of registers in the program
     *
     * @return Current number of registers
     */
    public int getNumberOfRegisters() {
        // Enter to number of registers screen
        RobotType.typeString("4", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        // Capture image
        BufferedImage image = this.robot.createScreenCapture(new Rectangle(365 + 70, 164 + 60, 140, 26));
        // Go to previous menu
        RobotType.typeString("\n", this.robot);
        // Analyze
        Tesseract1 ocr = new Tesseract1();
        ocr.setLanguage("spa");
        String result = "";
        try {
            result = ocr.doOCR(image);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        // Eliminate extra stuff and convert the string to a number
        return Integer.valueOf(result.replaceAll("[^0-9?!\\.]", ""));
    }

    public ArrayList<String> searchByName(String name) {
        ArrayList<String> result = new ArrayList<String>();
        // Enter to the search screen
        RobotType.typeString("7", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        RobotType.typeString("N\n", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        RobotType.typeString(name + "\n", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Create recognizer
        String finalConditionString = "";
        Tesseract1 ocr = new Tesseract1();
        ocr.setLanguage("spa");
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
        }
        int i = 0;
        do {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }

            BufferedImage gameImage = this.robot.createScreenCapture(new Rectangle(365 + 30, 164 + 60, 450, 25));
            File outputfile = new File("game" + i + ".png");
            i++;
            try {
                ImageIO.write(gameImage, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RobotType.typeString("N\n", this.robot);
            // Wait for the VM
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            BufferedImage recognizeEnd = this.robot.createScreenCapture(new Rectangle(365 + 360, 164 + 175, 50, 25));
            // Analyze game line
            String gameRecognized = "";
            try {
                gameRecognized = ocr.doOCR(gameImage);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
            // Analyze final condition
            try {
                finalConditionString = ocr.doOCR(recognizeEnd);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
            System.out.println("Recognize: \"" + gameRecognized.replaceAll("\n", "") + "\"");
        } while (finalConditionString.length() == 0);

        RobotType.typeString("\n", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        RobotType.typeString("N\n", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }

        return null;
    }

    /**
     * Close the program and kill the emulator
     */
    public void close() {
        RobotType.typeString("8", robot);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        RobotType.typeString("S\n", robot);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        this.dosbox.destroy();
    }
}
