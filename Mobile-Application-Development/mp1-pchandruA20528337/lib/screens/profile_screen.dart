import 'package:flutter/material.dart';
import 'package:cs442_mp1/data/profile_data.dart';

class ProfileScreen extends StatelessWidget {
  final ProfileData profileData = ProfileData();

  ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "My Profile",
          style: TextStyle(
            fontSize: 24.0,
            fontWeight: FontWeight.bold,
            color: Color.fromARGB(255, 7, 8, 9),
            letterSpacing: 0.2,
          ),
        ),
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: profileData.buildProfileContent(),
        ),
      ),
    );
  }
}
