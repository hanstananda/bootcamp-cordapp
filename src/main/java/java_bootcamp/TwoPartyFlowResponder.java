package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatedBy;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.NodeInfo;
import net.corda.core.node.ServiceHub;

import java.util.List;

@InitiatedBy(TwoPartyFlow.class)
public class TwoPartyFlowResponder extends FlowLogic<Void> {
    private FlowSession counterpartySession;

    public TwoPartyFlowResponder(FlowSession counterpartyflowSession){
        this.counterpartySession = counterpartyflowSession;
    }
    @Suspendable
    public Void call() throws FlowException {

        // network hub, to know more about nodes available, information about nodes
        ServiceHub servicehub = getServiceHub();
        //get house states
        List<StateAndRef<HouseState> >statesFromVault =
                servicehub.getVaultService().queryBy(HouseState.class).getStates();
        CordaX500Name alicesName = new CordaX500Name("Alice","Singapore","Singapore");
        // get node info
        NodeInfo alice = servicehub.getNetworkMapCache().getNodeByLegalName(alicesName);
        // get information about own node
        int platformVersion = servicehub.getMyInfo().getPlatformVersion();

        int receivedInt = counterpartySession.receive(Integer.class).unwrap(it->{
            if(it>3) throw new IllegalArgumentException("number too high!");
            return it;
        });

        int receivedIntPlusOne = receivedInt + 1;
        // send back the modified value
        counterpartySession.send(receivedIntPlusOne);
        return null;
    }
}
