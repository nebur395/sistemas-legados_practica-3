package connection;

import models.Program;
import ocr.OCR;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that abstracts the access to the legacy system
 */
public class MenaData {
    /**
     * Robot that allows to input keystrokes programmatically
     */
    public Robot robot;
    /**
     * DOSBox process
     */
    private Process dosbox;

    /**
     * DOSBox's window position
     */
    int dosboxX = 365;
    int dosboxY = 164;
    /**
     * Class constructor
     * Starts the Robot interface.
     */
    public MenaData() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            System.out.println("Interface Robot impossible to start. Shutting down.");
            System.exit(1);
        }
    }

    public void start() {
        this.start("");
    }

    public void start(String autoExecute) {
        // Create and run process and Robot
        ProcessBuilder builder = new ProcessBuilder(Const.EXECUTABLE_NAME);
        try {
            this.dosbox = builder.start();
        } catch (IOException e) {
        }
        // Autoexecute command, if given
        if (autoExecute != null && autoExecute.length() > 0) {
            RobotType.typeString(autoExecute, this.robot);
        }
        // Wait until the program is loaded and running
        try {
            Thread.sleep(1250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Get Window position
        this.getWindowPosition();
    }

    private void getWindowPosition() {
        ProcessBuilder wmctrlBuilder = new ProcessBuilder(Const.WMCTRL_COMMAND, "-lG");
        Process wmctrl = null;
        try {
            wmctrl = wmctrlBuilder.start();
            BufferedReader stdout = new BufferedReader(new InputStreamReader(wmctrl.getInputStream()));
            String line = "";
            while (null != (line = stdout.readLine())) {
                line = line.replaceAll("\\s+", " ");
                if (line.contains("DOSBox")) {
                    this.dosboxX = Integer.valueOf(line.split(" ")[2]);
                    this.dosboxY = Integer.valueOf(line.split(" ")[3]);
                    //System.out.println("X: " + this.dosboxX + ", Y: " + this.dosboxY);
                }
            }
            wmctrl.destroy();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            System.out.println("Unable to get DOSBox window position. Shutting down.");
            System.exit(1);
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current number of registers in the program
     *
     * @return Current number of registers
     */
    public int getNumberOfRegisters() throws IOException {
        // Enter to number of registers screen
        RobotType.typeString("4", this.robot);
        // Wait for the VM
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        // Capture image
        BufferedImage image = this.robot.createScreenCapture(new Rectangle(this.dosboxX + 78, this.dosboxY + 66, 8 * 15, 10));
        //ImageIO.write(image, "png", new File("out.png"));
        // Go to previous menu
        RobotType.typeString("\n", this.robot);
        // Analyze
        OCR ocr = new OCR("fonts");
        String result = ocr.getStringFromImage(image);

        // Eliminate extra stuff and convert the string to a number
        return Integer.valueOf(result.split("  ")[0]);
    }

    public ArrayList<Program> searchByName(String name) {
        ArrayList<Program> result = new ArrayList<>();
        // Enter to the search screen
        RobotType.typeString("7N\n" + name + "\n", this.robot);
        // Create recognizer
        OCR ocr = new OCR("fonts");
        String recognized;
        String finalConditionString;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        do {
            BufferedImage gameImage = this.robot.createScreenCapture(new Rectangle(this.dosboxX + 38, this.dosboxY + 66, 8 * 64, 10));
            recognized = ocr.getStringFromImage(gameImage);
            RobotType.typeString("N\n", this.robot);

            // Create object
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println(recognized);
            result.add(parseRegister(recognized.trim()));

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            BufferedImage recognizeEnd = this.robot.createScreenCapture(new Rectangle(this.dosboxX + 358, this.dosboxY + 178, 8, 10));

            finalConditionString = ocr.getCharacterFromImage(recognizeEnd);
        }
        while (finalConditionString.equals(" "));

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
        return result;
    }

    public ArrayList<Program> searchByTape(String tapeId) {
        ArrayList<Program> result = new ArrayList<>();

        OCR ocr = new OCR("fonts");
        // Enter to the correct screen
        RobotType.typeString("6" + tapeId + "\n", this.robot);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String checkEnd = "";
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            for (int i = 0; i < 18; i++) {
                BufferedImage register = this.robot.createScreenCapture(new Rectangle(this.dosboxX + 22, this.dosboxY + 66 + 16 * i, 8 * 76, 10));
                //try {
                //    ImageIO.write(register, "png", new File("result_" + i + ".png"));
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
                String registerString = ocr.getStringFromImage(register);
                if (registerString.substring(0, 9).equals("         ")) {
                    break;
                }
                if (tapeId.equals("") || Arrays.asList(registerString.substring(62, 71).trim().split("-")).contains(tapeId)) {
                    //System.out.println(registerString);
                    result.add(parseRegisterLine(registerString));
                    //parseRegisterLine().serialize());
                }
                //System.out.println(;
//                System.out.println(ocr.getStringFromImage(register));
            }
            RobotType.typeString(" ", this.robot);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkEnd = ocr.getStringFromImage(this.robot.createScreenCapture(new Rectangle(this.dosboxX + 246, this.dosboxY + 50, 8 * 7, 10)));
        } while (!checkEnd.equals("M E N U"));
        return result;
    }

    /**
     * Close the program and kill the emulator
     */
    public void close() {
        if (this.dosbox != null) {
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
            this.dosbox = null;
        }
    }

    private Program parseRegister(String s) {
        return new Program(s.split("  - ")[0],
            s.split("  - ")[1].split("   ")[0],
            s.split("  - ")[1].split("   ")[1],
            s.split("  - ")[1].split("   ")[2].split(":")[1]);
    }

    private Program parseRegisterLine(String s) {
        return new Program(
            s.substring(71, 76).trim(),
            s.substring(9, 41).trim(),
            s.substring(42, 62).trim(),
            s.substring(62, 71).trim()
        );
    }

}
