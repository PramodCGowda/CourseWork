import 'package:flutter/material.dart';
import 'models/dice.dart';
import 'models/scorecard.dart';

enum YahtzeeGameStatus {
  InProgress,
  GameOver,
}

class YahtzeeGameState with ChangeNotifier {
  final Dice dice;
  final ScoreCard scoreCard;
  int _diceTotal = 0;

  final List<ScoreCategory> _selectedCategories = [];

  YahtzeeGameStatus _gameStatus = YahtzeeGameStatus.InProgress;

  YahtzeeGameStatus get gameStatus => _gameStatus;

  YahtzeeGameState(this.dice, this.scoreCard);

  int get diceTotal => _diceTotal;

  List<ScoreCategory> get selectedCategory => _selectedCategories;

  void rollDice() {
    if (_gameStatus == YahtzeeGameStatus.InProgress) {
      dice.roll();
      updateScores();
      notifyListeners();
    }
  }

  void updateScores() {
    scoreCard.resetScores();
    for (ScoreCategory category in ScoreCategory.values) {
      scoreCard.registerScore(category, dice.values);
    }
  }

  void toggleHold(int index) {
    if (_gameStatus == YahtzeeGameStatus.InProgress) {
      dice.toggleHold(index);
      notifyListeners();
    }
  }

  void selectCategory(ScoreCategory category, BuildContext context) {
    if (_selectedCategories.contains(category)) {
      return;
    }
    _selectedCategories.add(category);
    scoreCard.selectedCategory(category);

    if (scoreCard.completed) {
      _showGameOverDialog(context);
      _gameStatus = YahtzeeGameStatus.GameOver;
    } else {
      endTurn();
    }
    notifyListeners();
  }

  void endTurn() {
    if (_gameStatus == YahtzeeGameStatus.InProgress) {
      dice.clearHolds();
      notifyListeners();
    }
  }

  void _showGameOverDialog(BuildContext context) {
    final currentTotal = scoreCard.currentTotal;

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text("Game Over!!!"),
          content: Text("Total Score: $currentTotal"),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.pop(context);
                resetGame();
              },
              child: const Text("Play Again"),
            ),
          ],
        );
      },
    );
  }

  void resetGame() {
    _gameStatus = YahtzeeGameStatus.InProgress;
    dice.clearHolds();
    scoreCard.resetCategories();
    _diceTotal = 0;
    _selectedCategories.clear();
    notifyListeners();
  }
}
