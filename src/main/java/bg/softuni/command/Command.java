package bg.softuni.command;

import bg.softuni.contract.Executable;
import bg.softuni.contract.Service;
import bg.softuni.framework.annotation.Inject;

public abstract class Command implements Executable {

    @Inject
    Service service;
}
