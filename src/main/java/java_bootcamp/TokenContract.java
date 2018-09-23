package java_bootcamp;

import jdk.nashorn.internal.parser.Token;
import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.Party;
import net.corda.core.transactions.LedgerTransaction;
import net.corda.nodeapi.internal.serialization.amqp.CustomSerializer;

import java.security.PublicKey;
import java.util.List;

import static net.corda.core.contracts.ContractsDSL.requireThat;

/* Our contract, governing how our state will evolve over time.
 * See src/main/kotlin/examples/ExampleContract.java for an example. */
public class TokenContract implements Contract {
    public static String ID = "java_bootcamp.TokenContract";

    @Override
    public void verify(LedgerTransaction tx) throws IllegalArgumentException {
        // this will be used to verify the contract
        if(tx.getInputStates().size()!=0)
        {
            throw new IllegalArgumentException("Transaction must have 0 inputs!");
        }
        if(tx.getOutputStates().size()!=1)
        {
            throw new IllegalArgumentException("Transaction must have 1 output!");
        }
        if(tx.getCommands().size()!=1)
        {
            throw new IllegalArgumentException("Transaction must have 1 command!");
        }

        ContractState output = tx.getOutput(0);
        Command command =tx.getCommand(0);

        if(!(output instanceof TokenState))
        {
            throw new IllegalArgumentException("Output must be a TokenState!");
        }
        if(!(command.getValue() instanceof Issue))
        {
            throw new IllegalArgumentException("Command must be a issue command!");
        }
        TokenState token = (TokenState)  output;
        if(token.getAmount()<0)
        {
            throw new IllegalArgumentException("Token amount must be positive");
        }

        List<PublicKey> requiredSigners = command.getSigners();
        Party issuer =token.getIssuer();
        PublicKey issuerKey = issuer.getOwningKey();
        if(!(requiredSigners.contains(issuerKey)))
        {
            throw new IllegalArgumentException("Issuer is required to be a signer!");
        }
    }

    public static class Issue implements CommandData {}
}