import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mp5/main.dart';
import 'package:mp5/views/addTask.dart';

void main() {
  group('Task Manager Integration Tests', () {
    testWidgets('Add and complete a task', (WidgetTester tester) async {
      await tester.pumpWidget(MyApp());
      await tester.pumpAndSettle();
      final addButton = find.byIcon(Icons.add);
      await tester.tap(addButton);
      await tester.pumpAndSettle();
      await tester.pumpWidget(AddTaskPage());
      final titleField = find.byKey(Key('Title'));
      await tester.enterText(titleField, 'New Task');
      final saveButton = find.text('Save');
      await tester.tap(saveButton);
      await tester.pumpAndSettle();
      expect(find.text('New Task'), findsOneWidget);
      final taskItem = find.text('New Task');
      await tester.tap(taskItem);
      await tester.pumpAndSettle();
      expect(find.text('New Task'), findsNothing);
    });
  });
}
