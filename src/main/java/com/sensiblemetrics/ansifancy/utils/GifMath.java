package com.sensiblemetrics.ansifancy.utils;

final class GifMath {
  private GifMath() {
  }

  static boolean isPowerOfTwo(int n) {
    return Integer.bitCount(n) == 1;
  }

  static int roundUpToPowerOfTwo(int n) {
    n--;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    n++;
    return n;
  }
}
