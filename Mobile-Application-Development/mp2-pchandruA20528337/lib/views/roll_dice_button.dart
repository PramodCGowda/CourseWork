import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/dice.dart';
import '../yahtzee_game_state.dart';

class RollDiceButton extends StatelessWidget {
  const RollDiceButton({super.key});

  @override
  Widget build(BuildContext context) {
    final dice = Provider.of<Dice>(context, listen: false);
    final gameState = Provider.of<YahtzeeGameState>(context);
    final remainingRolls = dice.rollsRemaining;

    return ElevatedButton(
      onPressed: (remainingRolls > 0)
          ? () {
              gameState.rollDice();
            }
          : null,
      style: ButtonStyle(
        backgroundColor: remainingRolls == 0
            ? MaterialStateProperty.all<Color>(Colors.grey)
            : MaterialStateProperty.all<Color>(Colors.blue),
      ),
      child: Text(
        style: const TextStyle(
          color: Colors.black,
        ),
        remainingRolls == 0
            ? 'Out of Rolls!'
            : 'Roll Dice ($remainingRolls)', // if 3 rolls completed set the button to out of Rolls until a category is choosen
      ),
    );
  }
}
