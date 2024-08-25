package io.artsok.scratchgame.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SymbolNotFoundException extends RuntimeException {

  private final String message;
}
