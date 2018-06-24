package java_bootcamp;

import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.transactions.LedgerTransaction;

import static net.corda.core.contracts.ContractsDSL.requireThat;

/* Our contract, governing how our state will evolve over time.
 * See src/main/kotlin/examples/ExampleContract.java for an example. */
// TODO: Implement the Contract interface.
public class TokenContract {
    public static String ID = "java_bootcamp.TokenContract";

    public static class Issue implements CommandData {}

    // TODO: Inside the verify method...
        // TODO: Check that there are zero inputs.

        // TODO: Check that there is one output.

        // TODO: Check that there is one command.

        // TODO: Check that the output is a TokenState.

        // TODO: Check that the output has a positive amount.

        // TODO: Check that the command is a TokenContract.Issue command.

        // TODO: Check that the command lists the output's issuer as a required signer.
}