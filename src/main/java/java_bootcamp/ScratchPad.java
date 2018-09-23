package java_bootcamp;

import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.Party;
import net.corda.core.transactions.TransactionBuilder;

import java.security.PublicKey;
import java.util.List;

public class ScratchPad {
    public static void main(String[] args)
    {
        StateAndRef<ContainerState> inputState = null;
        HouseState outputState = new HouseState("20 Nanyang Avenue", null);
        PublicKey requiredSigner = outputState.getOwner().getOwningKey();
        List<PublicKey> requiredSigners = ImmutableList.of(requiredSigner);
        Party notary = null;

        TransactionBuilder builder = new TransactionBuilder();

        builder.setNotary(notary);

        builder.addInputState(inputState)
                .addOutputState(outputState, "java_bootcamp.HouseContract")
        .addCommand(new HouseContract.Register(),requiredSigners);
        // contract in Outputstate must be passed a string! not a class, because it will create a trust issue if contract is also passed in the builder,
        // so instead of putting the contract to builder, only stirng given.

    }
}
