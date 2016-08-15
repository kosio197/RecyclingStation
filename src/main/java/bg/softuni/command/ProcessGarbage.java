package bg.softuni.command;

import bg.softuni.framework.annotation.Component;

@Component
public class ProcessGarbage extends Command {

    @Override
    public String execute(String command) {

        return super.service.addNewGarbage(command);
    }

}
