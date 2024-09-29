import 'package:flutter/material.dart';

class Investor {
  final String name;
  final String phone;
  final String entityNo;

  Investor({required this.name, required this.phone, required this.entityNo});
}

class InvestorsPage extends StatelessWidget {
  final List<Investor> investors = [
    Investor(name: 'Investor 1', phone: '123-456-7890', entityNo: 'E001'),
    Investor(name: 'Investor 2', phone: '234-567-8901', entityNo: 'E002'),
    Investor(name: 'Investor 3', phone: '345-678-9012', entityNo: 'E003'),
    Investor(name: 'Investor 4', phone: '456-789-0123', entityNo: 'E004'),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Investors'),
      ),
      body: ListView.builder(
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
      ),
    );
  }
}