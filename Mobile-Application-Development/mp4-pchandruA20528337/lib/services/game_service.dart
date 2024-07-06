import 'package:battleships/models/game_model.dart';
import 'package:battleships/utils/constants.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class GameService {
  static Future<List<Game>> fetchGames(
      String accessToken, bool showCompletedGames) async {
    final response = await http.get(
      Uri.parse('${AppConstants.baseUrl}/games'),
      headers: {'Authorization': 'Bearer $accessToken'},
    );

    if (response.statusCode == 200) {
      final gamesJson = jsonDecode(response.body)['games'];
      if (gamesJson is List) {
        final filteredGames = gamesJson
            .map<Game>((gameJson) => Game.fromJson(gameJson))
            .where((game) => showCompletedGames
                ? game.status == 1 || game.status == 2
                : game.status == 0 || game.status == 3)
            .toList();
        return filteredGames;
      }
    } else if (response.statusCode == 401) {
      final responseBodyLog = jsonDecode(response.body);
      if (responseBodyLog['message'] == AppConstants.invalidToken) {
        throw Exception('Invalid token');
      }
    }
    return [];
  }

  static Future<Game> createGame(
      String accessToken, Map<String, dynamic> requestBody) async {
    final response = await http.post(
      Uri.parse('${AppConstants.baseUrl}/games'),
      headers: {
        'Authorization': 'Bearer $accessToken',
        'Content-Type': 'application/json',
      },
      body: jsonEncode(requestBody),
    );

    if (response.statusCode == 200) {
      final gameJson = jsonDecode(response.body)['game'];
      final createdGame = Game.fromJson(gameJson);
      return createdGame;
    } else if (response.statusCode == 401) {
      final responseBodyLog = jsonDecode(response.body);
      if (responseBodyLog['message'] == AppConstants.invalidToken) {
        throw Exception('Invalid token');
      }
    }
    throw Exception('Failed to create game');
  }

  static Future<void> deleteGame(String accessToken, int gameId) async {
    final response = await http.delete(
      Uri.parse('${AppConstants.baseUrl}/games/$gameId'),
      headers: {'Authorization': 'Bearer $accessToken'},
    );

    if (response.statusCode == 200) {
      return;
    } else if (response.statusCode == 401) {
      final responseBodyLog = jsonDecode(response.body);
      if (responseBodyLog['message'] == AppConstants.invalidToken) {
        throw Exception('Invalid token');
      }
    }
    throw Exception('Failed to delete game');
  }
}
