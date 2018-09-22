package java_bootcamp;


import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.List;

public class ContainerState implements ContractState
{
    private int width, height, depth;
    private String contents;
    private Party owner;
    private Party carrier;

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ContainerState(int width, int height, int depth, String contents, Party owner, Party carrier) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.contents = contents;
        this.owner = owner;
        this.carrier = carrier;
    }

    public List<AbstractParty> getParticipants(){
        return ImmutableList.of(owner,carrier);
    }

    public static void main(String[] args)
    {
        Party jetcpackImporters = null;
        Party jetpackcarriers = null;
        ContainerState container = new ContainerState(
                2,
                4,
        2,
        "Jetpacks",
            jetcpackImporters,
                jetpackcarriers);
    }
}
