import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mp5/model/Task.dart';
import 'package:mp5/views/taskDetails.dart';

void main() {
  group('Task Detials Page Tests', () {
    testWidgets('Opens Task Details Page', (WidgetTester tester) async {
      final task = Task(
          id: 1,
          title: 'Test Task',
          description: 'This is a test task description',
          dueDate: DateTime.now().add(const Duration(days: 7)),
          label: 'Running',
          isCompleted: false);
      await tester.pumpWidget(MaterialApp(
          home: TaskDetailsPage(task: task, onUpdate: (updatedTask) {})));

      expect(find.text('Task Details'), findsOneWidget);
    });
  });
}
