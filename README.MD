## Overview

Problem statement: You need to build a scratch game, that will generate a matrix (for example 3x3)
from symbols(based on probabilities for each individual cell) and based on winning combintations
user either will win or lost.

## Building

Run next commands:

1. `cd ${projectDir}`
2. `mvn clean package`
3. `java -jar scratchgame-1.0-SNAPSHOT-jar-with-dependencies.jar --config /task/config.json --betting-amount 100`

the result would:

`Hello world, executable-jar-example!`

## What did I learn during the assignment?

```text
The @JsonAnySetter annotation is used to deserialize JSON when the JSON structure has dynamic or previously unknown keys. 
In your case, this annotation allows Jackson to dynamically add key-value pairs to the Map<String, WinCombination> 
during deserialization of JSON.
```

## TODO

1. Delete WinSymbolGroup
2. Check row and column, it's a valid int number (M x N != max of int)

## Nice to have (TODO)

1. Validation for all json structure and non-null fields.