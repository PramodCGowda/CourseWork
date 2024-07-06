// ignore_for_file: use_build_context_synchronously, library_private_types_in_public_api

import 'dart:math';

import 'package:flutter/material.dart';
import 'package:mp5/model/Task.dart';

class AddTaskPage extends StatefulWidget {
  const AddTaskPage({super.key});

  @override
  _AddTaskPageState createState() => _AddTaskPageState();
}

class _AddTaskPageState extends State<AddTaskPage> {
  late final List<String> _labels;
  late String _selectedLabel;

  @override
  void initState() {
    super.initState();
    _labels = ['Runnnig', 'Cricket', 'Barbeque', 'Other'];
    _selectedLabel = _labels[0];
  }

  final _formKey = GlobalKey<FormState>();
  final TextEditingController _titleController = TextEditingController();
  final TextEditingController _descriptionController = TextEditingController();
  DateTime _selectedDateTime = DateTime.now();
  void _saveTask() {
    if (_formKey.currentState!.validate()) {
      int id = DateTime.now().millisecondsSinceEpoch + Random().nextInt(9999);
      Task newTask = Task(
          id: id,
          title: _titleController.text,
          description: _descriptionController.text,
          dueDate: _selectedDateTime,
          label: _selectedLabel);
      Navigator.pop(context, newTask);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Add Task'),
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              TextFormField(
                controller: _titleController,
                decoration: const InputDecoration(labelText: 'Title'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a title';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _descriptionController,
                decoration: const InputDecoration(labelText: 'Description'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a description';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),
              DropdownButtonFormField<String>(
                value: _selectedLabel,
                onChanged: (newValue) {
                  setState(() {
                    _selectedLabel = newValue.toString();
                  });
                },
                items: _labels.asMap().entries.map((entry) {
                  String label = entry.value;
                  return DropdownMenuItem<String>(
                    value: label,
                    child: Text(label),
                  );
                }).toList(),
                decoration: const InputDecoration(
                  labelText: 'Label',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 16),
              TextButton.icon(
                onPressed: () async {
                  final now = DateTime.now();
                  final twelveHoursFromNow = now.add(const Duration(hours: 12));
                  DateTime? pickedDate = await showDatePicker(
                    context: context,
                    initialDate: twelveHoursFromNow,
                    firstDate: twelveHoursFromNow,
                    lastDate: DateTime(DateTime.now().year + 10),
                  );
                  if (pickedDate != null) {
                    TimeOfDay? pickedTime = await showTimePicker(
                      context: context,
                      initialTime: TimeOfDay.now(),
                    );
                    if (pickedTime != null) {
                      setState(() {
                        _selectedDateTime = DateTime(
                          pickedDate.year,
                          pickedDate.month,
                          pickedDate.day,
                          pickedTime.hour,
                          pickedTime.minute,
                        );
                      });
                    }
                  }
                },
                icon: const Icon(Icons.calendar_today),
                label: const Text('Select Date and Time'),
              ),
              Text(
                'Selected Date and Time: ${_selectedDateTime.year}-${_selectedDateTime.month.toString().padLeft(2, '0')}-${_selectedDateTime.day.toString().padLeft(2, '0')} ${_selectedDateTime.hour.toString().padLeft(2, '0')}:${_selectedDateTime.minute.toString().padLeft(2, '0')}',
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: _saveTask,
                child: const Text('Save'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
