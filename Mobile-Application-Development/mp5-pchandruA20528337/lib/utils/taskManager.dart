import 'dart:convert';
import 'package:mp5/model/task.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TaskManager {
  static const _keyTasks = 'tasks';

  Future<void> saveTasks(List<Task> tasks) async {
    final prefs = await SharedPreferences.getInstance();
    final encodedTasks = tasks.map((task) => task.toJson()).toList();
    await prefs.setString(_keyTasks, jsonEncode(encodedTasks));
  }

  Future<List<Task>> getTasks() async {
    final prefs = await SharedPreferences.getInstance();
    final tasksJson = prefs.getString(_keyTasks);
    if (tasksJson != null) {
      final List<dynamic> decodedTasks = jsonDecode(tasksJson);
      return decodedTasks.map((json) => Task.fromJson(json)).toList();
    }
    return [];
  }
}
