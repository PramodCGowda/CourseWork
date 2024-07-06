import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/dice.dart';

class DiceDisplay extends StatelessWidget {
  const DiceDisplay({super.key});

  @override
  Widget build(BuildContext context) {
    final dice = Provider.of<Dice>(context);
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: List.generate(
        dice.numDice,
        (index) => GestureDetector(
          onTap: () {
            dice.toggleHold(index);
          },
          child: Tooltip(
            message: dice.isHeld(index) ? 'Release Die' : 'Hold Die',
            child: Container(
              width: 50,
              height: 50,
              color: dice.isHeld(index) ? Colors.yellow : Colors.blue,
              child: Center(
                child: Text(
                  '${dice[index] ?? ''}',
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
