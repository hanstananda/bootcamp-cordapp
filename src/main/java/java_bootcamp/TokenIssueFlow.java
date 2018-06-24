package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;

/* Our flow, automating the process of updating the ledger.
 * See src/main/java/examples/IAmAFlowPair.java for an example. */
@InitiatingFlow
@StartableByRPC
public class TokenIssueFlow extends FlowLogic<SignedTransaction> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    private final Party owner;
    private final int amount;

    public TokenIssueFlow(Party owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        // We choose our transaction's notary (the notary prevents double-spends).
        Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        // TODO: Create a TransactionBuilder instance, setting the notary in the constructor.
        TransactionBuilder transactionBuilder = null;

        // TODO: Create the output TokenState.
            // TODO: Get our identity as the issuer.

            // TODO: Create a TokenState instance using our identity and the owner and amount fields.

        // TODO: Create the TokenContract.Issue command.
            // TODO: Create a TokenContract.Issue instance.

            // TODO: Get our (the issuer's) public key.

            // TODO: Create a Command instance using the command type and public key.

        // TODO: Add the output state and command to the transaction builder.

        // We check our transaction is valid based on its contracts.
        transactionBuilder.verify(getServiceHub());

        // We sign the transaction with our private key, making it immutable.
        SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(transactionBuilder);

        // We get the transaction notarised and recorded automatically by the platform.
        return subFlow(new FinalityFlow(signedTransaction));
    }
}