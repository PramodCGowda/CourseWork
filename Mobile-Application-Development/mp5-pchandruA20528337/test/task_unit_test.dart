import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mp5/model/task.dart'; // Import your Task model

void main() {
  group('Task Tests', () {
    test('Task creation and properties', () {
      final task = Task(
        id: 1,
        title: 'Test Task',
        description: 'This is a test task',
        dueDate: DateTime.now().add(const Duration(days: 7)),
        label: 'Work',
        isCompleted: false,
      );
      expect(task.id, 1);
      expect(task.title, 'Test Task');
      expect(task.description, 'This is a test task');
      expect(task.dueDate, isNotNull);
      expect(task.label, 'Work');
      expect(task.isCompleted, false);
    });

    test('Task completion status update', () {
      final task = Task(
        id: 1,
        title: 'Test Task',
        description: 'This is a test task',
        dueDate: DateTime.now().add(const Duration(days: 7)),
        label: 'Work',
        isCompleted: false,
      );
      task.toggleCompletion();
      expect(task.isCompleted, true);
    });
  });
}
