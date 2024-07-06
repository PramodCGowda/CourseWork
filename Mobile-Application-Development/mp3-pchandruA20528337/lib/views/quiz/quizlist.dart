import 'package:flutter/material.dart';
import 'package:mp3/models/datanotifier.dart';
import 'package:mp3/views/card/cardeditor.dart';
import 'package:mp3/views/quiz/quizpage.dart';
import 'package:provider/provider.dart';

enum SortMode { byID, alphabetical }

class QuizList extends StatefulWidget {
  final Decks deck;

  const QuizList({Key? key, required this.deck}) : super(key: key);

  @override
  State<QuizList> createState() => _QuizListState();
}

class _QuizListState extends State<QuizList> {
  SortMode _currentSortMode = SortMode.byID;

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    const double desiredCardWidth = 150.0;
    int cardsWidth = (screenWidth / desiredCardWidth).floor();
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromARGB(255, 27, 85, 167),
        title: Text(widget.deck.name, style: TextStyle(color: Colors.white)),
        actions: [
          IconButton(
            icon: _currentSortMode == SortMode.byID
                ? const Icon(Icons.access_time, color: Colors.white)
                : const Icon(Icons.sort_by_alpha, color: Colors.white),
            onPressed: () {
              setState(() {
                if (_currentSortMode == SortMode.byID) {
                  _currentSortMode = SortMode.alphabetical;
                } else {
                  _currentSortMode = SortMode.byID;
                }
              });
            },
          ),
          IconButton(
            icon: const Icon(Icons.play_arrow, color: Colors.white),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => QuizPlayPage(deck: widget.deck),
                ),
              );
            },
          )
        ],
      ),
      body: Consumer<DataNotifier>(
        builder: (context, dataNotifier, _) => FutureBuilder<List<Cards>>(
          future: dataNotifier.getCardsForDeck(widget.deck.id!),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.hasData) {
                List<Cards> cards = snapshot.data!;
                if (_currentSortMode == SortMode.byID) {
                  cards.sort((a, b) => a.id!.compareTo(b.id!));
                } else {
                  cards.sort((a, b) => a.question.compareTo(b.question));
                }

                if (cards.isNotEmpty) {
                  return GridView.builder(
                    padding: const EdgeInsets.all(5.0),
                    gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: cardsWidth,
                      childAspectRatio: 1,
                    ),
                    itemCount: cards.length,
                    itemBuilder: (context, index) {
                      final qcard = cards[index];
                      return _buildQuizCard(qcard);
                    },
                  );
                } else {
                  return const Center(child: Text('No cards in this deck.'));
                }
              } else {
                return const Center(child: CircularProgressIndicator());
              }
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CardEditor(deckId: widget.deck.id),
            ),
          );
        },
        backgroundColor: Color.fromARGB(255, 27, 85, 167),
        child: const Icon(
          Icons.add,
          color: Colors.white,
        ),
      ),
    );
  }

  Widget _buildQuizCard(Cards qcard) {
    return InkWell(
      onTap: () {
        Navigator.of(context).push(
          MaterialPageRoute(
            builder: (context) =>
                CardEditor(card: qcard, deckId: widget.deck.id),
          ),
        );
      },
      child: Card(
        color: Color.fromARGB(255, 75, 122, 181),
        child: Column(
          children: [
            Expanded(
              child: Center(
                child: Text(
                  qcard.question,
                  style: const TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
