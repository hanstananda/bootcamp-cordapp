package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;

@InitiatingFlow
@StartableByRPC
public class TwoPartyFlow extends FlowLogic<Integer> {
    private Party counterparty;
    private Integer number;

    public TwoPartyFlow(Party counterparty, Integer number){
        this.number = number;
        this.counterparty = counterparty;
    }


    @Suspendable
    public Integer call() throws FlowException {
        //start flow communication to counterparty
        FlowSession session = initiateFlow(counterparty);
        session.send(number);

        int receivedIncrementedInteger = session.receive(Integer.class).unwrap(it->it);
        return receivedIncrementedInteger;
    }
}
