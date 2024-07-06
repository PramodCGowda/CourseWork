import 'package:flutter/material.dart';

class ProfileHeader extends StatelessWidget {
  const ProfileHeader({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(4.0),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10.0),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.shade300.withOpacity(0.5),
            spreadRadius: 5,
            blurRadius: 3,
            offset: const Offset(0, 2),
          ),
        ],
        gradient: const LinearGradient(
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
          colors: [
            Color.fromARGB(255, 223, 225, 228),
            Color.fromRGBO(198, 208, 200, 0.375),
          ],
          stops: [0.0, 1.0],
        ),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            flex: 2,
            child: Padding(
              padding: const EdgeInsets.all(2.0),
              child: Column(
                children: [
                  Container(
                    width: 160,
                    height: 160,
                    decoration: const BoxDecoration(
                      shape: BoxShape.circle,
                      image: DecorationImage(
                        image: AssetImage('assets/images/IMG_0423.png'),
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(width: 16.0),
          const Expanded(
            flex: 3,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Pramoda Chandru',
                  style: TextStyle(
                    fontSize: 18.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                SizedBox(height: 1.0),
                Text(
                  'Master Student',
                  style: TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                SizedBox(height: 1.0),
                Row(
                  children: [
                    Icon(
                      Icons.phone,
                      color: Colors.red,
                    ),
                    SizedBox(width: 8.0),
                    Text('123-456-7890'),
                  ],
                ),
                Row(
                  children: [
                    Icon(Icons.email),
                    SizedBox(width: 8.0),
                    Text('pchandru@hawk.iit.edu'),
                  ],
                ),
                Row(
                  children: [
                    Icon(Icons.location_on),
                    SizedBox(width: 8.0),
                    Text('32 St, Chicago, IL, USA'),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
