# Self Notes

1. Remember to copy compier.xml from lib to the .idea or else the tests build will fail!!!

## General command:
```Bash
flow list
```
list every available flow that can be run by the party

## Command on PartyA:
```bash
flow start TokenIssueFlow owner: PartyB, amount: 99
```

Return value
```text
DONE
```

```Bash
run vaultQuery contractStateType: java_bootcamp.TokenState
```

Return value
```JSON
{
  "states" : [ {
    "state" : {
      "data" : {
        "issuer" : "O=PartyA, L=London, C=GB",
        "owner" : "O=PartyB, L=New York, C=US",
        "amount" : 99,
        "participants" : [ "O=PartyA, L=London, C=GB", "O=PartyB, L=New York, C=US" ]
      },
      "contract" : "java_bootcamp.TokenContract",
      "notary" : "O=Notary, L=London, C=GB",
      "encumbrance" : null,
      "constraint" : {
        "attachmentId" : "C78338C574B59CA7F80B08BE500D6B251845A037F1EE6010D940CE1127F9E5B9"
      }
    },
    "ref" : {
      "txhash" : "8C103E7F7DCB3BC1D7B504EACDD32389FEF8B8A4FEE4A8C5DFE98A635EE2948D",
      "index" : 0
    }
  } ],
  "statesMetadata" : [ {
    "ref" : {
      "txhash" : "8C103E7F7DCB3BC1D7B504EACDD32389FEF8B8A4FEE4A8C5DFE98A635EE2948D",
      "index" : 0
    },
    "contractStateClassName" : "java_bootcamp.TokenState",
    "recordedTime" : 1537724752.676000000,
    "consumedTime" : null,
    "status" : "UNCONSUMED",
    "notary" : "O=Notary, L=London, C=GB",
    "lockId" : null,
    "lockUpdateTime" : null
  } ],
  "totalStatesAvailable" : -1,
  "stateTypes" : "UNCONSUMED",
  "otherResults" : [ ]
}
```

## Command on PartyB:
```Bash
run vaultQuery contractStateType: java_bootcamp.TokenState
```
Return value
```JSON
{
  "states" : [ {
    "state" : {
      "data" : {
        "issuer" : "O=PartyA, L=London, C=GB",
        "owner" : "O=PartyB, L=New York, C=US",
        "amount" : 99,
        "participants" : [ "O=PartyA, L=London, C=GB", "O=PartyB, L=New York, C=US" ]
      },
      "contract" : "java_bootcamp.TokenContract",
      "notary" : "O=Notary, L=London, C=GB",
      "encumbrance" : null,
      "constraint" : {
        "attachmentId" : "C78338C574B59CA7F80B08BE500D6B251845A037F1EE6010D940CE1127F9E5B9"
      }
    },
    "ref" : {
      "txhash" : "8C103E7F7DCB3BC1D7B504EACDD32389FEF8B8A4FEE4A8C5DFE98A635EE2948D",
      "index" : 0
    }
  } ],
  "statesMetadata" : [ {
    "ref" : {
      "txhash" : "8C103E7F7DCB3BC1D7B504EACDD32389FEF8B8A4FEE4A8C5DFE98A635EE2948D",
      "index" : 0
    },
    "contractStateClassName" : "java_bootcamp.TokenState",
    "recordedTime" : 1537724753.929000000,
    "consumedTime" : null,
    "status" : "UNCONSUMED",
    "notary" : "O=Notary, L=London, C=GB",
    "lockId" : null,
    "lockUpdateTime" : null
  } ],
  "totalStatesAvailable" : -1,
  "stateTypes" : "UNCONSUMED",
  "otherResults" : [ ]
}

```

## Command on PartyC:
```Bash
run vaultQuery contractStateType: java_bootcamp.TokenState
```
Return value
```JSON
{
  "states" : [ ],
  "statesMetadata" : [ ],
  "totalStatesAvailable" : -1,
  "stateTypes" : "UNCONSUMED",
  "otherResults" : [ ]
}
```
This means that agreement between partyA and PartyB is not visible in partyC,
even though they are running on the same network!
