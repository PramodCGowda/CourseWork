import 'package:flutter/foundation.dart';
import 'package:mp3/utils/dbhelper.dart';
import 'package:flutter/services.dart' show rootBundle;
import 'dart:convert';

class DataNotifier extends ChangeNotifier {
  List<Decks> _decks = [];
  final Map<int, List<Cards>> _cardsByDeck = {};

  List<Decks> get decks => _decks;

  DataNotifier() {
    _loadDecks();
  }

  _loadDecks() async {
    final data = await DBHelper().query('decks');
    _decks = data.map((e) => Decks(id: e['id'], name: e['name'])).toList();
    notifyListeners();
  }

  Future<List<dynamic>> loadFlashcards() async {
    String jsonString = await rootBundle.loadString('assets/flashcards.json');
    return json.decode(jsonString);
  }

  Future<int> getCardsLength(int deckId) async {
    final data = await DBHelper().query('cards', where: 'deck_id = $deckId');
    return data.length;
  }

  Future<void> intDataFromJSONToDB() async {
    List<dynamic> flashcardsData = await loadFlashcards();

    for (var deckData in flashcardsData) {
      String deckTitle = deckData["title"];
      Decks newDeck = Decks(name: deckTitle);
      await saveDeck(newDeck);
      int deckId = newDeck.id!;

      for (var cardData in deckData["flashcards"]) {
        String question = cardData["question"];
        String answer = cardData["answer"];
        var existingCards = await getCardsForDeck(deckId);
        if (!existingCards.any((card) => card.question == question)) {
          Cards newCard =
              Cards(deckId: deckId, question: question, answer: answer);
          await saveCard(newCard);
        }
      }
    }
  }

  Future<List<Cards>> getCardsForDeck(int deckId) async {
    if (!_cardsByDeck.containsKey(deckId)) {
      final data = await DBHelper().query('cards', where: 'deck_id = $deckId');
      _cardsByDeck[deckId] = data
          .map((e) => Cards(
              id: e['id'],
              deckId: e['deck_id'],
              question: e['question'],
              answer: e['answer']))
          .toList();
      notifyListeners();
    }
    return _cardsByDeck[deckId]!;
  }

  Future<void> saveDeck(Decks deck) async {
    deck.id = await DBHelper().insert('decks', {'name': deck.name});
    _loadDecks();
  }

  Future<void> deleteDeck(Decks deck) async {
    if (deck.id != null) {
      await DBHelper().deleteWhere('cards', 'deck_id', deck.id!);
      await DBHelper().delete('decks', deck.id!);
      _decks.removeWhere((d) => d.id == deck.id);
      _cardsByDeck.remove(deck.id);
      notifyListeners();
    }
  }

  Future<void> updateDeck(Decks deck) async {
    if (deck.id != null) {
      await DBHelper().update('decks', {'name': deck.name}, deck.id!);
      int indexToUpdate = _decks.indexWhere((d) => d.id == deck.id);
      if (indexToUpdate != -1) {
        _decks[indexToUpdate].name = deck.name;
        notifyListeners();
      }
    }
  }

  Future<void> saveCard(Cards card) async {
    final dbHelper = DBHelper();
    if (card.id != null) {
      await dbHelper.update(
          'cards',
          {
            'question': card.question,
            'answer': card.answer,
            'deck_id': card.deckId,
          },
          card.id!);
    } else {
      card.id = await dbHelper.insert('cards', {
        'question': card.question,
        'answer': card.answer,
        'deck_id': card.deckId,
      });
    }

    if (_cardsByDeck.containsKey(card.deckId)) {
      _cardsByDeck.remove(card.deckId);
    }
    notifyListeners();
  }

  Future<void> deletecard(Cards card) async {
    if (card.id != null) {
      await DBHelper().delete('cards', card.id!);
      if (_cardsByDeck.containsKey(card.deckId)) {
        _cardsByDeck.remove(card.deckId);
      }
      notifyListeners();
    }
  }
}

class Decks {
  int? id;
  String name;

  Decks({this.id, required this.name});
}

class Cards {
  int? id;
  final int deckId;
  String question;
  String answer;

  Cards(
      {this.id,
      required this.deckId,
      required this.question,
      required this.answer});
}
