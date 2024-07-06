import 'package:flutter/material.dart';
import '../views/dice_display.dart';
import '../views/scorecard_display.dart';
import '../views/roll_dice_button.dart';

class YahtzeeGameScreen extends StatelessWidget {
  const YahtzeeGameScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Yahtzee'),
      ),
      body: const Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            DiceDisplay(),
            SizedBox(height: 30),
            Center(
              child: RollDiceButton(),
            ),
            SizedBox(height: 25),
            ScorecardDisplay(),
          ],
        ),
      ),
    );
  }
}
