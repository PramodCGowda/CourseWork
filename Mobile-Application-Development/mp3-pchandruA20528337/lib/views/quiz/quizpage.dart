import 'package:flutter/material.dart';
import 'package:mp3/models/datanotifier.dart';
import 'package:provider/provider.dart';

class QuizPlayPage extends StatefulWidget {
  final Decks deck;

  const QuizPlayPage({Key? key, required this.deck}) : super(key: key);

  @override
  QuizPlayPageState createState() => QuizPlayPageState();
}

class QuizPlayPageState extends State<QuizPlayPage> {
  bool showAnswer = false;
  int currentIndex = 0;
  int viewedCount = 1;
  int peekedCount = 0;
  Set<int> viewedCardsSet = {};
  Set<int> peekedCardsSet = {};
  late Future<List<Cards>> cardsFuture;

  @override
  void initState() {
    super.initState();
    cardsFuture = getAndShuffleCards();
    viewedCardsSet.add(currentIndex);
  }

  Future<List<Cards>> getAndShuffleCards() async {
    final cards = await Provider.of<DataNotifier>(context, listen: false)
        .getCardsForDeck(widget.deck.id!);
    return List.from(cards)..shuffle();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          backgroundColor: Color.fromARGB(255, 27, 85, 167),
          title: Text(widget.deck.name, style: TextStyle(color: Colors.white))),
      body: FutureBuilder<List<Cards>>(
        future: cardsFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            if (snapshot.hasData && snapshot.data!.isNotEmpty) {
              List<Cards> shuffledCards = snapshot.data!;
              return Column(
                children: [
                  Flexible(
                    flex: 7,
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Card(
                        color: showAnswer
                            ? Color.fromARGB(255, 28, 144, 49)
                            : Color.fromARGB(255, 75, 122, 181),
                        margin: const EdgeInsets.all(8.0),
                        child: Center(
                          child: Padding(
                            padding: const EdgeInsets.all(20.0),
                            child: Text(
                              showAnswer
                                  ? shuffledCards[currentIndex].answer
                                  : shuffledCards[currentIndex].question,
                              style: const TextStyle(
                                  fontSize: 18, color: Colors.white),
                              textAlign: TextAlign.center,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Flexible(
                    flex: 6,
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            IconButton(
                              icon: const Icon(Icons.arrow_back),
                              onPressed: () {
                                setState(() {
                                  if (currentIndex == 0) {
                                    currentIndex = shuffledCards.length - 1;
                                  } else {
                                    currentIndex--;
                                  }
                                  showAnswer = false;
                                  viewedCardsSet.add(currentIndex);
                                  viewedCount = viewedCardsSet.length;
                                });
                              },
                            ),
                            IconButton(
                              icon: showAnswer
                                  ? const Icon(Icons.flip_to_front)
                                  : const Icon(Icons.flip_to_back),
                              onPressed: () => setState(() {
                                showAnswer = !showAnswer;
                                if (showAnswer &&
                                    !peekedCardsSet.contains(currentIndex)) {
                                  peekedCount++;
                                  peekedCardsSet.add(currentIndex);
                                }
                              }),
                            ),
                            IconButton(
                              icon: const Icon(Icons.arrow_forward),
                              onPressed: () {
                                setState(() {
                                  if (currentIndex ==
                                      shuffledCards.length - 1) {
                                    currentIndex = 0;
                                  } else {
                                    currentIndex++;
                                  }
                                  showAnswer = false;
                                  viewedCardsSet.add(currentIndex);
                                  viewedCount = viewedCardsSet.length;
                                });
                              },
                            ),
                          ],
                        ),
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                  "Seen $viewedCount of ${shuffledCards.length} cards"),
                              const SizedBox(
                                height: 15,
                              ),
                              Text(
                                  "Peeked at $peekedCount of $viewedCount answers")
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              );
            } else {
              return const Center(child: Text('No cards in this deck.'));
            }
          } else {
            return const Center(child: CircularProgressIndicator());
          }
        },
      ),
    );
  }
}
