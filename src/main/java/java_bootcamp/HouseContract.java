package java_bootcamp;

import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.Party;
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
            // "Shape" constraints.
            if(tx.getInputStates().size()!=0)
                throw new IllegalArgumentException("Registration must have no input!");
            if(tx.getOutputStates().size()!=1)
                throw new IllegalArgumentException("Transaction must have one output!");

            // Content constraints.
            ContractState outputState = tx.getOutput(0);
            if(outputState instanceof HouseState)
                throw new IllegalArgumentException("Output must be housestate");
            HouseState houseState = (HouseState) outputState;
            if(houseState.getAddress().length()<=3)
                throw new IllegalArgumentException("Address must be longer than 3 characters");
            if(houseState.getOwner().getName().getCountry().equals("Brazil"))
                throw new IllegalArgumentException("Not allowed to register for Brazilian owners :(");
            // Required signer constraints.
            Party owner = houseState.getOwner();
            PublicKey ownersKey = owner.getOwningKey();
            if(!(requiredSigners.contains(ownersKey)))
                throw new IllegalArgumentException("Owner must sign the registration");
        }
        else if (commandType instanceof Transfer){
            // "Shape" constraints.
            if(tx.getOutputStates().size()!=1)
                throw new IllegalArgumentException("Must have one input!");
            if(tx.getOutputStates().size()!=1)
                throw new IllegalArgumentException("Transaction must have one output!");

            // Content constraints.
            ContractState output = tx.getOutput(0);
            ContractState input = tx.getInput(0);

            //check if i/o are HouseState
            if(!(input instanceof HouseState))
                throw new IllegalArgumentException("Input must be a HouseState");
            if(!(output instanceof HouseState))
                throw new IllegalArgumentException("Output must be a HouseState");

            //more detailed stuff
            HouseState inputHouse = (HouseState) input;
            HouseState outputHouse = (HouseState) output;

            if (inputHouse.getAddress().equals(outputHouse.getAddress()))
                throw new IllegalArgumentException("In a transfer, address cannot change!");
            if(inputHouse.getOwner().equals(outputHouse.getOwner()))
                throw new IllegalArgumentException("In a transfer, the owner must change!");

            // Signer constraints
            Party inputOwner = inputHouse.getOwner();
            Party outputOwner = outputHouse.getOwner();

            if(!(requiredSigners.contains(inputOwner.getOwningKey())))
                throw  new IllegalArgumentException("Current owner must sign the transfer!");
            if(!(requiredSigners.contains(outputOwner.getOwningKey())))
                throw  new IllegalArgumentException("New owner must sign the transfer!");
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
