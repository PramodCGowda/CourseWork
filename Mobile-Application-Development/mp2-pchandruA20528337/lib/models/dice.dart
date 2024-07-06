import 'package:collection/collection.dart';
import 'dart:math';
import 'package:flutter/material.dart';

class Dice extends ChangeNotifier {
  final List<int?> _values;
  final List<bool> _held;

  int _rollsRemaining = 3;

  final int numDice;

  Dice(this.numDice)
      : _values = List<int?>.filled(numDice, null),
        _held = List<bool>.filled(numDice, false);

  List<int> get values => List<int>.unmodifiable(_values.whereNotNull());

  int? operator [](int index) => _values[index];

  bool isHeld(int index) => _held[index];

  void clear() {
    for (var i = 0; i < _values.length; i++) {
      _values[i] = null;
      _held[i] = false;
    }
    notifyListeners();
  }

  void roll() {
    if (_rollsRemaining > 0) {
      for (var i = 0; i < _values.length; i++) {
        if (!_held[i]) {
          _values[i] = Random().nextInt(6) + 1;
        }
      }
      _rollsRemaining--;
      notifyListeners();
    }
  }

  void toggleHold(int index) {
    if (_rollsRemaining > 0 && _rollsRemaining < 3) {
      _held[index] = !_held[index];
      notifyListeners();
    }
  }

  void clearHolds() {
    _values.fillRange(0, _held.length); //clear values
    _held.fillRange(0, _held.length, false); // clear held dice placeholders
    rollsRemaining = 3; //reset dice rolls
    notifyListeners();
  }

  int get rollsRemaining => _rollsRemaining;

  set rollsRemaining(int value) {
    _rollsRemaining = value;
    notifyListeners();
  }
}
