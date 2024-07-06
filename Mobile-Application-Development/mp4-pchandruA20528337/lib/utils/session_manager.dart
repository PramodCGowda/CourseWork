import 'package:shared_preferences/shared_preferences.dart';

class SessionManager {
  static const String _tokenKey = 'session_token';
  static const String _usernameKey = 'username';

  /// Checks if a user is logged in by verifying the presence of a session token.
  /// Returns true if logged in, false otherwise.
  static Future<bool> isLoggedIn() async {
    final prefs = await SharedPreferences.getInstance();
    final sessionToken = prefs.getString(_tokenKey);
    return sessionToken != null;
  }

  /// Retrieves the session token from local storage.
  /// Returns an empty string if the token is not found.
  static Future<String> getSessionToken() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString(_tokenKey) ?? '';
  }

  /// Sets the session token in local storage.
  static Future<void> setSessionToken(String token) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(_tokenKey, token);
  }

  /// Clears the session data by removing both session token and username from local storage.
  static Future<void> clearSession() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(_tokenKey);
    await prefs.remove(_usernameKey); // Corrected the key name
  }

  /// Retrieves the username from local storage.
  /// Returns an empty string if the username is not found.
  static Future<String> getUsername() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString(_usernameKey) ?? '';
  }

  /// Sets the username in local storage.
  static Future<void> setUsername(String username) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(_usernameKey, username);
  }
}
