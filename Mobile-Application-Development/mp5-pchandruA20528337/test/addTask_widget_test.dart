import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mp5/views/addTask.dart';

void main() {
  group('Add Task Page Tests', () {
    testWidgets('Enters text in title field', (WidgetTester tester) async {
      // Arrange
      await tester.pumpWidget(const MaterialApp(home: AddTaskPage()));

      // Act
      final titleField = find.text('Title');
      await tester.enterText(titleField, 'Test Task');

      // Assert
      expect(find.text('Test Task'), findsOneWidget); // Check entered text
    });
  });
}
