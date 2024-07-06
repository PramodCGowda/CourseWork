import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/scorecard.dart';
import '../yahtzee_game_state.dart';

class ScorecardDisplay extends StatelessWidget {
  const ScorecardDisplay({super.key});

  @override
  Widget build(BuildContext context) {
    final scoreCard = Provider.of<ScoreCard>(context);
    final gameState = Provider.of<YahtzeeGameState>(context);

    List<ScoreCategory> leftCategories = ScoreCategory.values.sublist(0, 6);
    List<ScoreCategory> rightCategories = ScoreCategory.values.sublist(6);

    List<Widget> leftColumnWidgets = leftCategories.map((category) {
      return buildCategoryWidget(category, scoreCard, gameState, context);
    }).toList();

    List<Widget> rightColumnWidgets = rightCategories.map((category) {
      return buildCategoryWidget(category, scoreCard, gameState, context);
    }).toList();

    Widget totalScoreWidget = Center(
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 8.0),
        child: Text(
          'Current Score: ${scoreCard.currentTotal}',
          style: const TextStyle(fontWeight: FontWeight.bold),
        ),
      ),
    );

    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Row(
          children: [
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: leftColumnWidgets,
              ),
            ),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: rightColumnWidgets,
              ),
            ),
          ],
        ),
        const SizedBox(height: 20),
        totalScoreWidget,
      ],
    );
  }

  Widget buildCategoryWidget(ScoreCategory category, ScoreCard scoreCard,
      YahtzeeGameState gameState, BuildContext context) {
    final score = scoreCard.getScore(category);
    final categoryName = category.name;
    final isSelected = scoreCard.isSelected(category);

    return GestureDetector(
      onTap: () {
        if (gameState.dice.rollsRemaining < 3) {
          gameState.selectCategory(category,
              context); // trigger selectcategory when user chooses a category
        }
      },
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 4.0),
        child: RichText(
          text: TextSpan(
            style: const TextStyle(color: Colors.black),
            children: [
              TextSpan(
                text: "$categoryName: ",
                style:
                    const TextStyle(fontWeight: FontWeight.bold, fontSize: 15),
              ),
              const WidgetSpan(
                child: SizedBox(width: 20),
              ),
              TextSpan(
                text: '${score ?? 'pick'}', //default value is pick
                style: TextStyle(
                  decoration: TextDecoration.underline,
                  fontWeight: FontWeight.bold,
                  fontSize: 15,
                  color: isSelected
                      ? const Color.fromARGB(255, 11, 177, 17)
                      : (score != null ? Colors.blue : Colors.black),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
