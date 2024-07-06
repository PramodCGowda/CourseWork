import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'models/dice.dart';
import 'models/scorecard.dart';
import 'views/yahtzee_game_screen.dart';
import 'yahtzee_game_state.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => Dice(5)),
        ChangeNotifierProvider(create: (_) => ScoreCard()),
        ChangeNotifierProvider(
          create: (context) {
            final dice = Provider.of<Dice>(context, listen: false);
            final scoreCard = Provider.of<ScoreCard>(context, listen: false);
            return YahtzeeGameState(dice, scoreCard);
          },
        ),
      ],
      child: MaterialApp(
        title: 'Yahtzee App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: const YahtzeeGameScreen(),
      ),
    );
  }
}
