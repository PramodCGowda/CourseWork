import 'package:flutter/material.dart';
import 'package:mp3/models/datanotifier.dart';
import 'package:mp3/views/deck/decklist.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (context) => DataNotifier(),
      child: const MemorEase(),
    ),
  );
}

class MemorEase extends StatelessWidget {
  const MemorEase({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
        debugShowCheckedModeBanner: false, home: DeckList());
  }
}
