package java_bootcamp;

import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

import java.security.PublicKey;
import java.util.List;

public class HouseContract implements Contract {

    public void verify(LedgerTransaction tx) throws IllegalArgumentException {
        if(tx.getCommands().size()!=1)
        {
            throw new IllegalArgumentException("Transaction must have one command!");
        }
        Command command = tx.getCommand(0);
        List<PublicKey> requiredSigners = command.getSigners();
        CommandData commandType = command.getValue();

        //impose rules on certain command
        if (commandType instanceof Register)
        {
            //TODO("Registration transaction logic.")
        }
        else if (commandType instanceof Transfer){
            //TODO("Transfer transaction logic.")
        }
        else
        {
            throw new IllegalArgumentException("Command type not recognized.");
        }
        //throw new IllegalArgumentException("Accept nothing"); --> make it denies everything :)
    }
    // use this class to make those classes known as command list
    public class Register implements CommandData{

    }

    public class Transfer implements  CommandData{

    }
}
