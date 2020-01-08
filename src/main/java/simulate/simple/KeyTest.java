package simulate.simple;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-08 14:38
 * @Description: 键盘测试
 */
public class KeyTest {

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }
}
