import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:mp5/model/Task.dart';

class WeatherUtil {
  static String apiKey = '0fbdf397ccd54315978bd274583645e4';
  static String city = 'Chicago';

  static Future<String> fetchWeather(Task task) async {
    String dueDate = getTaskDate(task);

    String apiUrl =
        'https://api.weatherbit.io/v2.0/forecast/hourly?city=$city&key=$apiKey&hours=240';

    http.Response response = await http.get(Uri.parse(apiUrl));

    if (response.statusCode == 200) {
      Map<String, dynamic> weatherData = jsonDecode(response.body);
      int index = checkDate(weatherData, dueDate);
      return index == -1
          ? 'no status'
          : weatherData['data'][index]['weather']['description'];
    } else {
      print('Failed to load weather data');
      return 'Failed to load weather data';
    }
  }

  static String getTaskDate(Task task) {
    final dateTime = task.dueDate;
    String formattedDate =
        '${dateTime.year}-${dateTime.month.toString().padLeft(2, '0')}-${dateTime.day.toString().padLeft(2, '0')}:${dateTime.hour.toString().padLeft(2, '0')}';
    return formattedDate;
  }

  static int checkDate(Map<String, dynamic> weatherData, String dueDate) {
    for (int i = 0; i < weatherData['data'].length; i++) {
      if (weatherData['data'][i]['datetime'] == dueDate) {
        print("found matching date at $i");
        return i;
      }
    }
    return -1;
  }
}
