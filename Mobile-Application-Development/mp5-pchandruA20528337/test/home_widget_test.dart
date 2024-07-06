import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mp5/utils/constants.dart';
import 'package:mp5/views/home.dart';

void main() {
  group('HomePage Tests', () {
    testWidgets('Initial state - no tasks', (WidgetTester tester) async {
      // Arrange
      await tester.pumpWidget(const MaterialApp(home: HomePage()));

      // Assert
      final taskListText = find.text(AppConstants.noTask);
      expect(taskListText, findsOneWidget);
    });

    //   testWidgets('Add a task and verify it shows up in the list',
    //       (WidgetTester tester) async {
    //     // Arrange
    //     await tester.pumpWidget(const MaterialApp(home: HomePage()));

    //     // Act
    //     final fab = find.byType(FloatingActionButton);
    //     await tester.tap(fab);
    //     await tester.pumpAndSettle();

    //     expect(find.byType(AddTaskPage), findsOneWidget);

    //     final labelDropdown = find.byType(DropdownButtonFormField);
    //     // expect(labelDropdown, findsOneWidget);

    //     if (labelDropdown != null) {
    //       await tester.tap(labelDropdown);
    //       final runningLabel =
    //           find.text('Running');
    //       await tester.tap(runningLabel);
    //     } else {
    //       print("I'm here");
    //     }

    //     await tester
    //         .pumpAndSettle();
    //     final titleField = find.text('Name');
    //     await tester.enterText(titleField, 'Test Task');
    //     final descriptionField = find.text('Description');
    //     await tester.enterText(
    //         descriptionField, 'This is a test task description');
    //     final saveButton = find.text('Save');
    //     await tester.tap(saveButton);

    //     final taskTitle = find.text('Name: Test Task');
    //     // expect(taskTitle, findsOneWidget);
    //   });
  });
}
