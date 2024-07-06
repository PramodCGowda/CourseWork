import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class Task {
  final int id;
  final String title;
  final String description;
  final DateTime dueDate;
  final String label;
  bool isCompleted;

  Task({
    required this.id,
    required this.title,
    required this.description,
    required this.dueDate,
    required this.label,
    this.isCompleted = false,
  });

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'title': title,
      'description': description,
      'dueDate': dueDate.toIso8601String(),
      'label': label,
      'isCompleted': isCompleted,
    };
  }

  factory Task.fromJson(Map<String, dynamic> json) {
    int id = json['id'] as int? ?? 0;
    return Task(
      id: id,
      title: json['title'],
      description: json['description'],
      dueDate: DateTime.parse(json['dueDate']),
      label: json['label'],
      isCompleted: json['isCompleted'],
    );
  }

  static Future<List<Task>> getTasksFromPrefs() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String>? taskList = prefs.getStringList('tasks');
    if (taskList == null) {
      return [];
    }
    return taskList
        .map((taskJson) => Task.fromJson(jsonDecode(taskJson)))
        .toList();
  }

  Future<void> saveTaskToPrefs() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> taskList = prefs.getStringList('tasks') ?? [];
    bool taskExists = false;
    int existingTaskIndex = -1;
    for (int i = 0; i < taskList.length; i++) {
      Map<String, dynamic> taskJson = jsonDecode(taskList[i]);
      Task task = Task.fromJson(taskJson);
      if (task.id == this.id) {
        taskExists = true;
        existingTaskIndex = i;
        break;
      }
    }
    if (taskExists && existingTaskIndex != -1) {
      taskList[existingTaskIndex] = jsonEncode(this.toJson());
    } else {
      taskList.add(jsonEncode(this.toJson()));
    }
    await prefs.setStringList('tasks', taskList);
  }

  void toggleCompletion() {
    isCompleted = !isCompleted;
  }

  Future<void> deleteTaskFromPrefs() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String>? taskList = prefs.getStringList('tasks');

    if (taskList != null) {
      taskList.removeWhere((taskJson) {
        Map<String, dynamic> taskMap = jsonDecode(taskJson);
        return taskMap['id'] == id;
      });
      await prefs.setStringList('tasks', taskList);
    }
  }

  Future<void> updateTaskInPrefs() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String>? tasksJson = prefs.getStringList('tasks');

    if (tasksJson != null) {
      tasksJson = tasksJson.map((taskJson) {
        Map<String, dynamic> taskMap = json.decode(taskJson);
        if (taskMap['id'] == id) {
          taskMap['title'] = title;
          taskMap['description'] = description;
          taskMap['dueDate'] = dueDate.toIso8601String();
          taskMap['isCompleted'] = isCompleted;
        }
        return json.encode(taskMap);
      }).toList();

      prefs.setStringList('tasks', tasksJson);
    }
  }
}
