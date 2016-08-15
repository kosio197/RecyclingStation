package bg.softuni.command.interpreter;

import bg.softuni.command.ProcessGarbage;
import bg.softuni.command.Status;
import bg.softuni.command.TimeToRecycle;
import bg.softuni.contract.Executable;
import bg.softuni.contract.Interpreter;
import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.annotation.Inject;
import bg.softuni.framework.object.contract.ObjectContainer;

@Component
public class CommandInterpreter implements Interpreter {

    @Inject
    ObjectContainer container;


    @Override
    public String interpretCommand(String command) {
        Executable currentCommand;

        currentCommand = getCommandInstance(command);
        if (command == null) {
            throw new IllegalArgumentException("Invalid command" + command);
        }

        return currentCommand.execute(command);
    }

    private Executable getCommandInstance(String command) {
        String commandName = command.split("\\|")[0];

        Executable executable = null;

        if (commandName.startsWith("ProcessGarbage")) {
            executable = container.getInstance(ProcessGarbage.class);
        } else if (commandName.equals("Status")) {
            executable = container.getInstance(Status.class);
        } else if (commandName.equals("TimeToRecycle")) {
            executable = container.getInstance(TimeToRecycle.class);
        }

        return executable;
    }


}
