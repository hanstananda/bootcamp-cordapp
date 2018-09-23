package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;

//Initiating flow means it will be initiated once blockchain started
//initatiedby means it need something to be initiated by another class
//startable by rpc means it can be started by operator node directly
@InitiatingFlow
@StartableByRPC
public class VerySimpleFlow extends FlowLogic<Integer> {

    // Suspendable is used to make the flow can be suspended between checkpoint
    // Must be added manually!
    @Suspendable
    public Integer call() throws FlowException {
        int a=1;
        int b=2;

        return a+b;
    }
}
