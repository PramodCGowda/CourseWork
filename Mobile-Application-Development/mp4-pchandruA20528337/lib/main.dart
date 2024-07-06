import 'package:battleships/utils/constants.dart';
import 'package:battleships/views/login_view.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
      debugShowCheckedModeBanner: false,
      title: AppConstants.appName,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const LoginScreen()));
}
