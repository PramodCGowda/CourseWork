import 'package:flutter/material.dart';

Widget buildPhoto(String imageUrl) {
  return Padding(
    padding: const EdgeInsets.only(left: 10.0),
    child: Container(
      width: 60,
      height: 60,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8.0),
        image: DecorationImage(
          image: AssetImage(imageUrl),
          fit: BoxFit.fitWidth,
        ),
      ),
    ),
  );
}
