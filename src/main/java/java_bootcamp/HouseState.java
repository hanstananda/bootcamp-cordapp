package java_bootcamp;

import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class HouseState implements ContractState{

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return ImmutableList.of();
    }

}
