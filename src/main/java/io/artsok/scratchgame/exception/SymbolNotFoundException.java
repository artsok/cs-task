package io.artsok.scratchgame.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SymbolNotFoundException extends RuntimeException {

  private final String message;
}
