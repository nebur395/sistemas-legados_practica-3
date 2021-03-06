package connection;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class RobotType {

    public static void typeString(String string, Robot robot) {
        for (char i : string.toCharArray()) {
            typeChar(i, robot);
        }
    }

    private static void typeChar(char character, Robot robot) {
        switch (character) {
            case 'a':
                doType(robot, VK_A);
                break;
            case 'b':
                doType(robot, VK_B);
                break;
            case 'c':
                doType(robot, VK_C);
                break;
            case 'd':
                doType(robot, VK_D);
                break;
            case 'e':
                doType(robot, VK_E);
                break;
            case 'f':
                doType(robot, VK_F);
                break;
            case 'g':
                doType(robot, VK_G);
                break;
            case 'h':
                doType(robot, VK_H);
                break;
            case 'i':
                doType(robot, VK_I);
                break;
            case 'j':
                doType(robot, VK_J);
                break;
            case 'k':
                doType(robot, VK_K);
                break;
            case 'l':
                doType(robot, VK_L);
                break;
            case 'm':
                doType(robot, VK_M);
                break;
            case 'n':
                doType(robot, VK_N);
                break;
            case 'o':
                doType(robot, VK_O);
                break;
            case 'p':
                doType(robot, VK_P);
                break;
            case 'q':
                doType(robot, VK_Q);
                break;
            case 'r':
                doType(robot, VK_R);
                break;
            case 's':
                doType(robot, VK_S);
                break;
            case 't':
                doType(robot, VK_T);
                break;
            case 'u':
                doType(robot, VK_U);
                break;
            case 'v':
                doType(robot, VK_V);
                break;
            case 'w':
                doType(robot, VK_W);
                break;
            case 'x':
                doType(robot, VK_X);
                break;
            case 'y':
                doType(robot, VK_Y);
                break;
            case 'z':
                doType(robot, VK_Z);
                break;
            case 'A':
                doType(robot, VK_SHIFT, VK_A);
                break;
            case 'B':
                doType(robot, VK_SHIFT, VK_B);
                break;
            case 'C':
                doType(robot, VK_SHIFT, VK_C);
                break;
            case 'D':
                doType(robot, VK_SHIFT, VK_D);
                break;
            case 'E':
                doType(robot, VK_SHIFT, VK_E);
                break;
            case 'F':
                doType(robot, VK_SHIFT, VK_F);
                break;
            case 'G':
                doType(robot, VK_SHIFT, VK_G);
                break;
            case 'H':
                doType(robot, VK_SHIFT, VK_H);
                break;
            case 'I':
                doType(robot, VK_SHIFT, VK_I);
                break;
            case 'J':
                doType(robot, VK_SHIFT, VK_J);
                break;
            case 'K':
                doType(robot, VK_SHIFT, VK_K);
                break;
            case 'L':
                doType(robot, VK_SHIFT, VK_L);
                break;
            case 'M':
                doType(robot, VK_SHIFT, VK_M);
                break;
            case 'N':
                doType(robot, VK_SHIFT, VK_N);
                break;
            case 'O':
                doType(robot, VK_SHIFT, VK_O);
                break;
            case 'P':
                doType(robot, VK_SHIFT, VK_P);
                break;
            case 'Q':
                doType(robot, VK_SHIFT, VK_Q);
                break;
            case 'R':
                doType(robot, VK_SHIFT, VK_R);
                break;
            case 'S':
                doType(robot, VK_SHIFT, VK_S);
                break;
            case 'T':
                doType(robot, VK_SHIFT, VK_T);
                break;
            case 'U':
                doType(robot, VK_SHIFT, VK_U);
                break;
            case 'V':
                doType(robot, VK_SHIFT, VK_V);
                break;
            case 'W':
                doType(robot, VK_SHIFT, VK_W);
                break;
            case 'X':
                doType(robot, VK_SHIFT, VK_X);
                break;
            case 'Y':
                doType(robot, VK_SHIFT, VK_Y);
                break;
            case 'Z':
                doType(robot, VK_SHIFT, VK_Z);
                break;
            case '`':
                doType(robot, VK_BACK_QUOTE);
                break;
            case '0':
                doType(robot, VK_0);
                break;
            case '1':
                doType(robot, VK_1);
                break;
            case '2':
                doType(robot, VK_2);
                break;
            case '3':
                doType(robot, VK_3);
                break;
            case '4':
                doType(robot, VK_4);
                break;
            case '5':
                doType(robot, VK_5);
                break;
            case '6':
                doType(robot, VK_6);
                break;
            case '7':
                doType(robot, VK_7);
                break;
            case '8':
                doType(robot, VK_8);
                break;
            case '9':
                doType(robot, VK_9);
                break;
            case '-':
                doType(robot, VK_MINUS);
                break;
            case '=':
                doType(robot, VK_EQUALS);
                break;
            case '~':
                doType(robot, VK_SHIFT, VK_BACK_QUOTE);
                break;
            case '!':
                doType(robot, VK_EXCLAMATION_MARK);
                break;
            case '@':
                doType(robot, VK_AT);
                break;
            case '#':
                doType(robot, VK_NUMBER_SIGN);
                break;
            case '$':
                doType(robot, VK_DOLLAR);
                break;
            case '%':
                doType(robot, VK_SHIFT, VK_5);
                break;
            case '^':
                doType(robot, VK_CIRCUMFLEX);
                break;
            case '&':
                doType(robot, VK_AMPERSAND);
                break;
            case '*':
                doType(robot, VK_ASTERISK);
                break;
            case '(':
                doType(robot, VK_LEFT_PARENTHESIS);
                break;
            case ')':
                doType(robot, VK_RIGHT_PARENTHESIS);
                break;
            case '_':
                doType(robot, VK_UNDERSCORE);
                break;
            case '+':
                doType(robot, VK_PLUS);
                break;
            case '\t':
                doType(robot, VK_TAB);
                break;
            case '\n':
                doType(robot, VK_ENTER);
                break;
            case '[':
                doType(robot, VK_OPEN_BRACKET);
                break;
            case ']':
                doType(robot, VK_CLOSE_BRACKET);
                break;
            case '\\':
                doType(robot, VK_BACK_SLASH);
                break;
            case '{':
                doType(robot, VK_SHIFT, VK_OPEN_BRACKET);
                break;
            case '}':
                doType(robot, VK_SHIFT, VK_CLOSE_BRACKET);
                break;
            case '|':
                doType(robot, VK_SHIFT, VK_BACK_SLASH);
                break;
            case ';':
                doType(robot, VK_SEMICOLON);
                break;
            case ':':
                doType(robot, VK_SHIFT, VK_SEMICOLON);
                break;
            case '\'':
                doType(robot, VK_QUOTE);
                break;
            case '"':
                doType(robot, VK_QUOTEDBL);
                break;
            case ',':
                doType(robot, VK_COMMA);
                break;
            case '<':
                doType(robot, VK_SHIFT, VK_COMMA);
                break;
            case '.':
                doType(robot, VK_PERIOD);
                break;
            case '>':
                doType(robot, VK_SHIFT, VK_PERIOD);
                break;
            case '/':
                doType(robot, VK_SLASH);
                break;
            case '?':
                doType(robot, VK_SHIFT, VK_SLASH);
                break;
            case ' ':
                doType(robot, VK_SPACE);
                break;
            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

    private static void typeNumPad(Robot robot, int digit) {
        switch (digit) {
            case 0:
                doType(robot, VK_NUMPAD0);
                break;
            case 1:
                doType(robot, VK_NUMPAD1);
                break;
            case 2:
                doType(robot, VK_NUMPAD2);
                break;
            case 3:
                doType(robot, VK_NUMPAD3);
                break;
            case 4:
                doType(robot, VK_NUMPAD4);
                break;
            case 5:
                doType(robot, VK_NUMPAD5);
                break;
            case 6:
                doType(robot, VK_NUMPAD6);
                break;
            case 7:
                doType(robot, VK_NUMPAD7);
                break;
            case 8:
                doType(robot, VK_NUMPAD8);
                break;
            case 9:
                doType(robot, VK_NUMPAD9);
                break;
        }
    }


    private static void doType(Robot robot, int... keyCodes) {
        for (int i : keyCodes) {
            robot.keyPress(i);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        for (int i : keyCodes) {
            robot.keyRelease(i);
        }
    }
}
