## Overview

Problem statement: You need to build a scratch game, that will generate a matrix (for example 3x3)
from symbols(based on probabilities for each individual cell) and based on winning combintations
user either will win or lost.

## Assumptions

1. I didn't handle for the scenario where the configuration file doesn't have the correct order
   of probabilities. The length of standard_symbols in probabilities should be the same as size of
   matrix.
2. I focused on predefined symbols from A to F. Missed from G to Z.

## Building

Run next commands:

1. `cd ${projectDir}`
2. `mvn clean package`
3. `java -jar scratchgame-1.0-SNAPSHOT-jar-with-dependencies.jar --config /task/config.json --betting-amount 100`

the result would be (depends on configuration file):

```json
{
  "matrix": [
    [
      "A",
      "A",
      "B"
    ],
    [
      "A",
      "+1000",
      "B"
    ],
    [
      "A",
      "A",
      "B"
    ]
  ],
  "reward": 6600,
  "applied_winning_combinations": {
    "A": [
      "same_symbol_5_times",
      "same_symbols_vertically"
    ],
    "B": [
      "same_symbol_3_times",
      "same_symbols_vertically"
    ]
  },
  "applied_bonus_symbol": "+1000"
}
```

### Decomposed task

1. Deserialization of JSON (Prepared all necessary POJOs). - DONE
2. Generate 2D matrix - DONE
3. 

## What did I learn during the assignment?

```text
The @JsonAnySetter annotation is used to deserialize JSON when the JSON structure has dynamic or previously unknown keys. 
In your case, this annotation allows Jackson to dynamically add key-value pairs to the Map<String, WinCombination> 
during deserialization of JSON.
```

## TODO

1. Delete WinSymbolGroup
2. Check row and column, it's a valid int number (M x N != max of int). Can't be 0 x 0
3. Check negative bet_amount and 0, infinite

## Nice to have (TODO)

1. Validation for all json structure and non-null fields.
2. 