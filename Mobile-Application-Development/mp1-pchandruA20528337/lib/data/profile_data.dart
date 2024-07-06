import 'package:flutter/material.dart';
import 'package:cs442_mp1/widgets/education_item.dart';
import 'package:cs442_mp1/widgets/project_item.dart';

class ProfileData {
  Widget buildProfileHeader() {
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
                        image: AssetImage('assets/images/user.png'),
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
                  'Master\'s Student',
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
                      color: Color.fromRGBO(47, 106, 155, 1),
                    ),
                    SizedBox(width: 8.0),
                    Text('123-456-7890'),
                  ],
                ),
                Row(
                  children: [
                    Icon(
                      Icons.email,
                      color: Color.fromRGBO(47, 106, 155, 1),
                    ),
                    SizedBox(width: 8.0),
                    Text('pchandru@hawk.iit.edu'),
                  ],
                ),
                Row(
                  children: [
                    Icon(
                      Icons.location_on,
                      color: Color.fromRGBO(47, 106, 155, 1),
                    ),
                    SizedBox(width: 8.0),
                    Text('123 St, Chicago, IL, USA'),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget buildSectionContainer({required String title, required Widget child}) {
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
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(
            title,
            style: const TextStyle(
              fontSize: 24.0,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 8.0),
          child,
        ],
      ),
    );
  }

  Widget buildAboutMe() {
    return const Padding(
      padding: EdgeInsets.symmetric(horizontal: 4.0),
      child: Text(
        'I am a driven and enthusiastic student at the Illinois Institute of Technology, deeply passionate about mobile application development. Armed with a solid proficiency in programming languages such as Dart and Swift, particularly within the Flutter framework, I am eager to embark on the journey of crafting innovative solutions and crafting user-friendly mobile applications.',
        style: TextStyle(
          fontSize: 16.0,
        ),
      ),
    );
  }

  Widget buildInterests() {
    List<String> interests = [
      'Coding',
      'Hiking',
      'Cricket',
      'Travelling',
    ];

    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 4.0),
      child: Wrap(
        spacing: 6.0,
        children: interests.map((interest) {
          return Container(
            padding:
                const EdgeInsets.symmetric(horizontal: 12.0, vertical: 6.0),
            decoration: BoxDecoration(
              color: Colors.blueGrey,
              borderRadius: BorderRadius.circular(20.0),
            ),
            child: Text(
              interest,
              style: const TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.w600,
                  fontSize: 16),
            ),
          );
        }).toList(),
      ),
    );
  }

  Widget buildEducation() {
    List<Map<String, String>> educationData = [
      {
        'degree': 'Master of Computer Science',
        'institution': 'Illinois Institute of Technology',
        'GPA': '3.5 GPA',
        'imagePath': 'assets/images/image_iit.png',
      },
      {
        'degree': 'Bachelor of Information Science and Engineering',
        'institution': 'Visvesvaraya Technological University',
        'GPA': '3.2 GPA',
        'imagePath': 'assets/images/image_vtu.png',
      },
    ];
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 4.0),
      child: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: educationData.map((data) {
            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                EducationItem(
                    degree: data['degree']!,
                    institution: data['institution']!,
                    gpa: data['GPA']!,
                    imagePath: data['imagePath']!),
                const SizedBox(height: 8),
              ],
            );
          }).toList(),
        ),
      ),
    );
  }

  Widget buildProjects() {
    List<Map<String, String>> projectData = [
      {
        'title': 'Repair Mate',
        'description':
            'Developed a user-friendly web application with React and JavaScript, including modular components and seamless payment integration, while managing backend infrastructure deployment and database communication using Node.js, Express.js, MySQL, and MongoDB.',
        'imagePath': 'assets/images/repairmate.png',
      },
      {
        'title': 'Vending Machine',
        'description':
            'Developed and deployed two Vending Machine (VM) components using Object-Oriented design patterns, adhering to the principles of Model-Driven Architecture (MDA). Implemented the State Pattern to manage various states such as "Idle" and "Dispensing," demonstrating proficiency in design patterns and software development techniques. Utilized the Strategy Pattern to reduce payment processing time by 15% and implemented the Abstract Factory Pattern to enhance Product Dispenser creation efficiency by 20% and improve system scalability.',
        'imagePath': 'assets/images/vm.png',
      },
      {
        'title': 'Smart Homes',
        'description':
            'Developed a servlet-based web application for online orders on the Smart Homes site, leading to a 25% increase in user engagement and a 20% enhancement in order efficiency. Implemented session caching mechanisms to improve performance and facilitate seamless management of smart home appliances in the cart, optimizing the checkout process for increased efficiency.',
        'imagePath': 'assets/images/smarthome.png',
      },
    ];
    return ListView(
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
      children: projectData.map((data) {
        return ProjectItem(
          imageUrl: data['imagePath']!,
          title: data['title']!,
          description: data['description']!,
        );
      }).toList(),
    );
  }

  List<Widget> buildProfileContent() {
    return [
      buildProfileHeader(),
      const SizedBox(height: 20.0),
      buildSectionContainer(title: 'About Me', child: buildAboutMe()),
      const SizedBox(height: 20.0),
      buildSectionContainer(title: 'Interests', child: buildInterests()),
      const SizedBox(height: 20.0),
      buildSectionContainer(title: 'Education', child: buildEducation()),
      const SizedBox(height: 20.0),
      buildSectionContainer(title: 'Projects', child: buildProjects()),
      const SizedBox(height: 20.0),
    ];
  }
}
