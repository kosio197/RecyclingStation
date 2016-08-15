package bg.softuni.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import bg.softuni.command.interpreter.CommandInterpreter;
import bg.softuni.framework.object.component.ComponentContainer;
import bg.softuni.framework.object.contract.ObjectContainer;

public class Main {


    public static void main(String[] args) throws IOException {
        solve(System.in, System.out);
    }

    public static void solve(InputStream stream, PrintStream printStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        ObjectContainer container = new ComponentContainer();
        CommandInterpreter interpreter = container.getInstance(CommandInterpreter.class);

        while (true) {
            String command = br.readLine();
            String result = interpreter.interpretCommand(command);

            if (result != null) {
                if (result.equals("TimeToRecycle")) {
                    break;
                } else {
                    printStream.print(result);
                }
            }
        }
    }

}
