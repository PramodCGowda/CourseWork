import 'package:flutter/material.dart';
import 'package:mp3/models/datanotifier.dart';
import 'package:provider/provider.dart';

class CardEditor extends StatefulWidget {
  final Cards? card;
  final int? deckId;

  const CardEditor({Key? key, this.card, this.deckId}) : super(key: key);

  @override
  CardEditorState createState() => CardEditorState();
}

class CardEditorState extends State<CardEditor> {
  final _formKey = GlobalKey<FormState>();
  late TextEditingController _questionController;
  late TextEditingController _answerController;

  @override
  void initState() {
    super.initState();
    _questionController =
        TextEditingController(text: widget.card?.question ?? '');
    _answerController = TextEditingController(text: widget.card?.answer ?? '');
  }

  Future<void> _handleDelete(DataNotifier dataNotifier) async {
    if (widget.card != null && widget.card!.id != null) {
      await dataNotifier.deletecard(widget.card!);
      if (context.mounted) {
        Navigator.of(context).pop();
      }
    }
  }

  Future<void> _handleSaveOrUpdate(DataNotifier dataNotifier) async {
    if (widget.card != null) {
      widget.card!.question = _questionController.text;
      widget.card!.answer = _answerController.text;
      await dataNotifier.saveCard(widget.card!);
    } else {
      final newCard = Cards(
          deckId: widget.deckId!,
          question: _questionController.text,
          answer: _answerController.text);
      await dataNotifier.saveCard(newCard);
    }
    if (context.mounted) {
      Navigator.of(context).pop();
    }
  }

  @override
  @override
  Widget build(BuildContext context) {
    final dataNotifier = Provider.of<DataNotifier>(context, listen: true);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromARGB(255, 27, 85, 167),
        title: const Text('Edit Card', style: TextStyle(color: Colors.white)),
      ),
      body: Form(
        key: _formKey,
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                controller: _questionController,
                decoration: const InputDecoration(
                  hintText: '',
                  labelText: 'Question',
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a question';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16.0),
              TextFormField(
                controller: _answerController,
                decoration: const InputDecoration(
                  hintText: '',
                  labelText: 'Answer',
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter an answer';
                  }
                  return null;
                },
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor:
                          MaterialStateProperty.all(Colors.transparent),
                      shadowColor:
                          MaterialStateProperty.all(Colors.transparent),
                    ),
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        _handleSaveOrUpdate(dataNotifier);
                      }
                    },
                    child: const Text(
                      'Save',
                      style: TextStyle(color: Color.fromARGB(255, 27, 85, 167)),
                    ),
                  ),
                  const SizedBox(
                    width: 16,
                  ),
                  if (widget.card != null) ...[
                    ElevatedButton(
                      style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all(Colors.transparent),
                        shadowColor:
                            MaterialStateProperty.all(Colors.transparent),
                      ),
                      onPressed: () => _handleDelete(dataNotifier),
                      child: const Text(
                        'Delete',
                        style:
                            TextStyle(color: Color.fromARGB(255, 27, 85, 167)),
                      ),
                    ),
                  ],
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
