import 'package:flutter/material.dart';
import 'package:mp3/models/datanotifier.dart';
import 'package:mp3/views/deck/deckeditor.dart';
import 'package:mp3/views/quiz/quizlist.dart';
import 'package:provider/provider.dart';

class DeckList extends StatefulWidget {
  const DeckList({super.key});

  @override
  State<DeckList> createState() => _DeckListState();
}

class _DeckListState extends State<DeckList> {
  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    const double desiredCardWidth = 200.0;
    int cardsWidth = (screenWidth / desiredCardWidth).floor();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 27, 85, 167),
        title: const Text(
          'Flashcard Decks',
          style: TextStyle(
            color: Colors.white,
            fontSize: 18.0,
            fontWeight: FontWeight.bold,
          ),
        ),
        centerTitle: true,
        actions: <Widget>[
          IconButton(
            icon: const Icon(Icons.download_sharp),
            color: Colors.white,
            onPressed: () async {
              final dataNotifier =
                  Provider.of<DataNotifier>(context, listen: false);
              await dataNotifier
                  .intDataFromJSONToDB(); //insert the data from json to database and fetch it to UI
            },
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const DeckEditor(),
            ),
          );
        },
        backgroundColor: const Color.fromARGB(255, 27, 85, 167),
        child: const Icon(Icons.add, color: Colors.white),
      ),
      body: Consumer<DataNotifier>(
        builder: (context, dataNotifier, _) => GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: cardsWidth,
            childAspectRatio: 1,
          ),
          itemCount: dataNotifier.decks.length,
          itemBuilder: (context, index) {
            final deck = dataNotifier.decks[index];
            return _buildDeck(deck);
          },
        ),
      ),
    );
  }

  Widget _buildDeck(Decks deck) {
    return FutureBuilder<int>(
      future: Provider.of<DataNotifier>(context, listen: false)
          .getCardsLength(deck.id!),
      builder: (context, snapshot) {
        int noOfCards = snapshot.data ?? 0;
        return InkWell(
          onTap: () => Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) => QuizList(deck: deck),
            ),
          ),
          child: Card(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15.0),
            ),
            color: const Color.fromARGB(255, 75, 122, 181),
            child: Stack(
              children: [
                Center(
                  child: Text(
                    deck.name,
                    textAlign: TextAlign.center,
                    style: const TextStyle(fontSize: 18, color: Colors.white),
                  ),
                ),
                Align(
                  alignment: Alignment.bottomCenter,
                  child: Padding(
                    padding: const EdgeInsets.only(bottom: 20.0),
                    child: Text(
                      '$noOfCards cards',
                      style: const TextStyle(fontSize: 14, color: Colors.white),
                    ),
                  ),
                ),
                Positioned(
                  top: 0,
                  right: 0,
                  child: Material(
                    type: MaterialType.transparency,
                    child: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        InkWell(
                          borderRadius: BorderRadius.circular(24),
                          onTap: () {
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (context) => DeckEditor(deck: deck),
                              ),
                            );
                          },
                          child: const Padding(
                            padding: EdgeInsets.all(8.0),
                            child: Icon(
                              Icons.edit,
                              color: Colors.white,
                              size: 20,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
