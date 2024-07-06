// ignore_for_file: library_private_types_in_public_api

import 'package:flutter/material.dart';
import 'package:mp5/model/Task.dart';
import 'package:mp5/utils/constants.dart';
import 'package:mp5/views/addTask.dart';
import 'package:mp5/views/taskDetails.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<Task> tasks = [];
  String? _selectedLabel = 'Filter by Label';
  String? _selectedStatus = 'Pending';

  @override
  void initState() {
    super.initState();
    _loadTasks();
  }

  Future<void> _loadTasks() async {
    List<Task> loadedTasks = await Task.getTasksFromPrefs();
    setState(() {
      tasks = loadedTasks;
    });
  }

  void addTask(Task newTask) {
    setState(() {
      tasks.add(newTask);
      newTask.saveTaskToPrefs();
    });
  }

  void deleteTask(int index) {
    setState(() {
      tasks[index].deleteTaskFromPrefs();
      tasks.removeAt(index);
    });
  }

  void markTaskCompleted(int index) {
    setState(() {
      if (!tasks[index].isCompleted) {
        tasks[index].isCompleted = !tasks[index].isCompleted;
        tasks[index].saveTaskToPrefs();
      }
    });
  }

  void _navigateToTaskDetails(Task task) async {
    final updatedTask = await Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => TaskDetailsPage(
          task: task,
          onUpdate: (updatedTask) {
            setState(() {
              // Update the task in HomePage with the updatedTask
              tasks[tasks.indexWhere(
                  (element) => element.id == updatedTask.id)] = updatedTask;
            });
          },
        ),
      ),
    );
  }

  List<Task> _filteredTasks() {
    if (_selectedLabel == 'Filter by Label' &&
        _selectedStatus == 'Filter by Status') {
      return tasks;
    } else if (_selectedLabel != 'Filter by Label' &&
        _selectedStatus == 'Filter by Status') {
      return tasks.where((task) => task.label == _selectedLabel).toList();
    } else if (_selectedLabel == 'Filter by Label' &&
        _selectedStatus != 'Filter by Status') {
      return tasks
          .where((task) => task.isCompleted == (_selectedStatus == 'Completed'))
          .toList();
    } else {
      return tasks.where((task) {
        bool labelCondition = task.label == _selectedLabel;
        bool statusCondition = _selectedStatus == null ||
            (task.isCompleted && _selectedStatus == 'Completed') ||
            (!task.isCompleted && _selectedStatus == 'Pending');
        return labelCondition && statusCondition;
      }).toList();
    }
  }

  @override
  Widget build(BuildContext context) {
    List<Task> filteredTasks = _filteredTasks();

    return Scaffold(
      appBar: AppBar(
        title: const Text(AppConstants.appName),
        backgroundColor: Colors.blue,
      ),
      body: Column(
        children: [
          DropdownButtonFormField<String>(
            value: _selectedLabel,
            onChanged: (newValue) {
              setState(() {
                _selectedLabel = newValue;
              });
            },
            items:
                ['Filter by Label', 'Runnnig', 'Cricket', 'Barbeque', 'Other']
                    .map((label) => DropdownMenuItem<String>(
                          value: label,
                          child: Text(label),
                        ))
                    .toList(),
            decoration: const InputDecoration(
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 10),
          DropdownButtonFormField<String>(
            value: _selectedStatus,
            onChanged: (newValue) {
              setState(() {
                _selectedStatus = newValue;
              });
            },
            items: [
              'Filter by Status',
              'Completed',
              'Pending',
            ]
                .map((status) => DropdownMenuItem<String>(
                      value: status,
                      child: Text(status),
                    ))
                .toList(),
            decoration: const InputDecoration(
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 10),
          Expanded(
            child: (filteredTasks.isEmpty && tasks.isEmpty)
                ? const Center(
                    child: Text(AppConstants.noTask),
                  )
                : (filteredTasks.isEmpty)
                    ? const Center(
                        child: Text(AppConstants.noTaskForLabel),
                      )
                    : ListView.builder(
                        itemCount: filteredTasks.length,
                        itemBuilder: (context, index) {
                          final task = filteredTasks[index];
                          final backgroundColor = task.isCompleted
                              ? Colors.green[300]
                              : (tasks[index].dueDate.isBefore(DateTime.now()
                                      .add(const Duration(hours: 12))))
                                  ? Colors.red[200]
                                  : Colors.grey;
                          return Dismissible(
                            key: Key(filteredTasks[index].title),
                            onDismissed: (direction) {
                              deleteTask(index);
                              setState(() {});
                            },
                            child: ListTile(
                              title:
                                  Text('Name: ${filteredTasks[index].title}'),
                              subtitle: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                      'Description: ${filteredTasks[index].description}'),
                                  Text(
                                    'Due: ${filteredTasks[index].dueDate.year}-${filteredTasks[index].dueDate.month.toString().padLeft(2, '0')}-${filteredTasks[index].dueDate.day.toString().padLeft(2, '0')} ${filteredTasks[index].dueDate.hour.toString().padLeft(2, '0')}:${filteredTasks[index].dueDate.minute.toString().padLeft(2, '0')}',
                                  ),
                                  Text('Label: ${filteredTasks[index].label}'),
                                  Text(
                                      'Status: ${filteredTasks[index].isCompleted ? 'Completed' : 'Pending'}'),
                                ],
                              ),
                              trailing: Checkbox(
                                value: filteredTasks[index].isCompleted,
                                onChanged: (value) {
                                  markTaskCompleted(index);
                                },
                              ),
                              onTap: () {
                                _navigateToTaskDetails(filteredTasks[index]);
                              },
                              tileColor: backgroundColor,
                            ),
                          );
                        },
                      ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final newTask = await Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const AddTaskPage(),
            ),
          );
          if (newTask != null) {
            addTask(newTask);
          }
        },
        backgroundColor: Colors.blue,
        child: const Icon(
          Icons.add,
          color: Colors.white,
        ),
      ),
    );
  }
}
