import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class Investor {
  final String name;
  final String phone;
  final String entityNo;

  Investor({required this.name, required this.phone, required this.entityNo});
}

class InvestorsPage extends StatelessWidget {
  Future<List<Investor>> fetchInvestors() async {
    final response = await http.get(Uri.parse('http://localhost:8080/api/investors'));
    List<Investor> investors = [];

    if (response.statusCode == 200) {
      List<dynamic> data = jsonDecode(response.body);
      // String test = '[{"name":"John Doe","phone":"1234567890","entityNo":"123456"},{"name":"Jane Doe","phone":"0987654321","entityNo":"654321"}]';
      // data = jsonDecode(test);
      investors = data.map((item) => item as Map<String, dynamic>).map((item) {
        return Investor(
          name: item['name'] ?? '',
          phone: item['phone'] ?? '',
          entityNo: item['entityNo'] ?? '',
        );
      }).cast<Investor>().toList();
    } else {
      throw Exception('Server down');
    }

    print('Investors List: ${investors}');
    return investors;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Investors'),
      ),
      body: FutureBuilder<List<Investor>>(
        future: fetchInvestors(),
        builder: (context, snapshot) {
          print(snapshot.data);

          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Failed to load investors'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return Center(child: Text('No investors found'));
          } else {
            final investors = snapshot.data!;
            return ListView.builder(
              itemCount: investors.length,
              itemBuilder: (context, index) {
                return ListTile(
                  title: Text(investors[index].name),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Phone: ${investors[index].phone}'),
                      Text('Entity No: ${investors[index].entityNo}'),
                    ],
                  ),
                );
              },
            );
          }
        },
      ),
    );
  }
}