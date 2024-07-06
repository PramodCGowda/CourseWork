// ignore_for_file: library_private_types_in_public_api, use_build_context_synchronously

import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:mp5/model/Task.dart';
import 'package:mp5/utils/WeatherUtil.dart';

typedef TaskUpdateCallback = void Function(Task updatedTask);

class TaskDetailsPage extends StatefulWidget {
  final Task task;
  final TaskUpdateCallback onUpdate;

  const TaskDetailsPage(
      {super.key, required this.task, required this.onUpdate});

  @override
  _TaskDetailsPageState createState() => _TaskDetailsPageState();
}

class _TaskDetailsPageState extends State<TaskDetailsPage>
    with SingleTickerProviderStateMixin {
  late TextEditingController _titleController;
  late TextEditingController _descriptionController;
  late DateTime _dueDate;
  String? _label;
  bool _isEditing = false;
  String? _weatherCondition;
  late AnimationController _animationController;
  late Animation<double> _animationOpacity;
  DateTime daysFromNow = DateTime.now().add(const Duration(days: 10));
  static const Color textColor = Colors.green;
  final now = DateTime.now();

  @override
  void initState() {
    super.initState();
    _titleController = TextEditingController(text: widget.task.title);
    _descriptionController =
        TextEditingController(text: widget.task.description);
    _dueDate = widget.task.dueDate;
    _weatherCondition = null;
    _label = widget.task.label;

    _animationController = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 500),
    );

    _animationOpacity = Tween<double>(begin: 0.0, end: 1.0).animate(
      CurvedAnimation(parent: _animationController, curve: Curves.easeIn),
    );
  }

  @override
  void dispose() {
    _titleController.dispose();
    _descriptionController.dispose();
    _animationController.dispose();
    super.dispose();
  }

  void _toggleEdit() {
    setState(() {
      _isEditing = !_isEditing;
    });
  }

  void _saveChanges() {
    Task updatedTask = Task(
      id: widget.task.id,
      title: _titleController.text,
      description: _descriptionController.text,
      dueDate: _dueDate,
      label: _label.toString(),
      isCompleted: widget.task.isCompleted,
    );

    _toggleEdit();
    updatedTask.updateTaskInPrefs().then((_) {
      setState(() {});
      widget.onUpdate(updatedTask);
    });
  }

  void _navigateToWeatherPage() async {
    _weatherCondition = await WeatherUtil.fetchWeather(widget.task);
    setState(() {
      print(_weatherCondition);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Task Details'),
        backgroundColor: Colors.blue,
        actions: [
          if (_isEditing)
            IconButton(
              icon: const Icon(Icons.save),
              onPressed: _saveChanges,
            ),
          IconButton(
            icon: _isEditing
                ? const Icon(Icons.cancel)
                : widget.task.isCompleted
                    ? const Icon(Icons.lock_outline)
                    : const Icon(Icons.edit),
            onPressed: _toggleEdit,
          )
        ],
      ),
      body: Stack(
        children: [
          AnimatedBuilder(
            animation: _animationController,
            builder: (_, __) {
              String backgroundImage;
              if (_weatherCondition?.toLowerCase().contains('rain') ?? false) {
                backgroundImage = 'assets/rain.jpg';
              } else if (_weatherCondition?.toLowerCase().contains('clouds') ??
                  false) {
                backgroundImage = 'assets/cloud.jpg';
              } else if (_weatherCondition?.toLowerCase().contains('clear') ??
                  false) {
                backgroundImage = 'assets/sun.jpg';
              } else {
                backgroundImage = 'assets/task.jpg';
              }
              return Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage(backgroundImage),
                    fit: BoxFit.cover,
                  ),
                ),
                child: BackdropFilter(
                  filter: ImageFilter.blur(sigmaX: 0, sigmaY: 0),
                  child: Container(
                    color: Colors.black.withOpacity(0.4),
                  ),
                ),
              );
            },
          ),
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text('Name:',
                    style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: textColor)),
                _isEditing
                    ? TextField(
                        controller: _titleController,
                        style: const TextStyle(color: textColor),
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(),
                          hintStyle: TextStyle(color: textColor),
                          labelStyle: TextStyle(color: textColor),
                        ))
                    : Text(_titleController.text,
                        style: const TextStyle(
                            color: textColor, fontWeight: FontWeight.bold)),
                const SizedBox(height: 16),
                const Text(
                  'Description:',
                  style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                      color: textColor),
                ),
                _isEditing
                    ? TextField(
                        controller: _descriptionController,
                        maxLines: 4,
                        style: const TextStyle(color: textColor),
                        decoration:
                            const InputDecoration(border: OutlineInputBorder()),
                      )
                    : Text(_descriptionController.text,
                        style: const TextStyle(
                          color: textColor,
                          fontWeight: FontWeight.bold,
                        )),
                const SizedBox(height: 16),
                const Text(
                  'Due Date:',
                  style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                      color: textColor),
                ),
                TextButton.icon(
                  onPressed: _isEditing
                      ? () async {
                          final twelveHoursFromNow =
                              now.add(const Duration(hours: 12));
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
                                _dueDate = DateTime(
                                  pickedDate.year,
                                  pickedDate.month,
                                  pickedDate.day,
                                  pickedTime.hour,
                                  pickedTime.minute,
                                );
                              });
                            }
                          }
                        }
                      : null,
                  icon: const Icon(Icons.calendar_today, color: textColor),
                  label: Text(
                      '${_dueDate.year}-${_dueDate.month.toString().padLeft(2, '0')}-${_dueDate.day.toString().padLeft(2, '0')} ${_dueDate.hour.toString().padLeft(2, '0')}:${_dueDate.minute.toString().padLeft(2, '0')}',
                      style: const TextStyle(
                        color: textColor,
                        fontWeight: FontWeight.bold,
                      )),
                ),
                const SizedBox(height: 16),
                const Text(
                  'Label:',
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                    color: textColor,
                  ),
                ),
                _isEditing
                    ? DropdownButtonFormField<String>(
                        value: _label ?? widget.task.label,
                        onChanged: (newValue) {
                          setState(() {
                            _label = newValue!;
                          });
                        },
                        items: [
                          'Filter by Label',
                          'Runnnig',
                          'Cricket',
                          'Barbeque',
                          'Other'
                        ].map((label) {
                          return DropdownMenuItem<String>(
                            value: label,
                            child: Text(label),
                          );
                        }).toList(),
                        style: const TextStyle(
                          color: textColor,
                          fontWeight: FontWeight.bold,
                        ),
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(),
                          hintStyle: TextStyle(color: textColor),
                          labelStyle: TextStyle(color: textColor),
                        ),
                      )
                    : Text(
                        _label.toString(),
                        style: const TextStyle(
                          color: textColor,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                const SizedBox(height: 16),
                TextButton(
                  onPressed: _dueDate.isAfter(daysFromNow)
                      ? null
                      : _navigateToWeatherPage,
                  style: ButtonStyle(
                    backgroundColor: _dueDate.isAfter(daysFromNow)
                        ? MaterialStateProperty.all<Color>(Colors.grey)
                        : MaterialStateProperty.all<Color>(textColor),
                    foregroundColor: MaterialStateProperty.resolveWith<Color?>(
                      (Set<MaterialState> states) {
                        if (states.contains(MaterialState.disabled)) {
                          return Colors.black;
                        }
                        return Colors.black;
                      },
                    ),
                  ),
                  child: Tooltip(
                    message: _dueDate.isAfter(daysFromNow)
                        ? 'Due is after 10 days cannot check weather condition'
                        : '',
                    child: const Text(
                      'Check Weather Condition',
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }
}
