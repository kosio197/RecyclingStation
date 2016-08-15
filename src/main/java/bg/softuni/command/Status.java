package bg.softuni.command;

import bg.softuni.framework.annotation.Component;

@Component
public class Status extends Command {

    @Override
    public String execute(String command) {
        return super.service.getStatusOfStation();
    }

}
