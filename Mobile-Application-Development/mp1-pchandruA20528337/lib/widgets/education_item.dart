import 'package:flutter/material.dart';
import 'photo_widget.dart';

class EducationItem extends StatelessWidget {
  final String degree;
  final String institution;
  final String gpa;
  final String imagePath;

  const EducationItem(
      {super.key,
      required this.degree,
      required this.institution,
      required this.gpa,
      required this.imagePath});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          buildPhoto(imagePath),
          const SizedBox(width: 10),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  degree,
                  style: const TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Text(
                  institution,
                  style: const TextStyle(overflow: TextOverflow.visible),
                ),
                Text(gpa),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
