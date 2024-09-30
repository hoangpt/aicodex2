import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:posapp/investors_page.dart';
import 'package:http/http.dart' as http;
import './investors_page_test.mocks.dart';

@GenerateMocks([http.Client])
void main() {
  group('InvestorsPage', () {
    testWidgets('displays a list of investors', (WidgetTester tester) async {
      final client = MockClient();

      when(client.get(Uri.parse('http://localhost:8080/api/investors')))
          .thenAnswer((_) async => http.Response(
              '[{"name":"John Doe","phone":"1234567890","entityNo":"123456"},{"name":"Jane Doe","phone":"0987654321","entityNo":"654321"}]',
              200));

      await tester.pumpWidget(MaterialApp(home: InvestorsPage()));

      await tester.pumpAndSettle(); // Trigger the FutureBuilder

      expect(find.text('John Doe'), findsOneWidget);
      expect(find.text('Jane Doe'), findsOneWidget);
      // expect(find.text('Phone: 1234567890'), findsOneWidget);
      // expect(find.text('Phone: 0987654321'), findsOneWidget);
      // expect(find.text('Entity No: 123456'), findsOneWidget);
      // expect(find.text('Entity No: 654321'), findsOneWidget);
    });

    testWidgets('displays a loading indicator while fetching investors',
        (WidgetTester tester) async {
      final client = MockClient();

      when(client.get(Uri.parse('http://localhost:8080/api/investors')))
          .thenAnswer((_) async => Future.delayed(
              const Duration(seconds: 2),
              () => http.Response(
                  '[{"name":"John Doe","phone":"1234567890","entityNo":"123456"}]',
                  200)));

      await tester.pumpWidget(MaterialApp(home: InvestorsPage()));

      expect(find.byType(CircularProgressIndicator), findsOneWidget);

      await tester.pump(const Duration(seconds: 2)); // Wait for the Future to complete

      expect(find.byType(CircularProgressIndicator), findsNothing);
    });

    testWidgets('displays an error message when fetching investors fails',
        (WidgetTester tester) async {
      final client = MockClient();

      when(client.get(Uri.parse('http://localhost:8080/api/investors')))
          .thenAnswer((_) async => http.Response('Not Found', 404));

      await tester.pumpWidget(MaterialApp(home: InvestorsPage()));

      await tester.pump(); // Trigger the FutureBuilder

      expect(find.text('Failed to load investors'), findsOneWidget);
    });

    testWidgets('displays a message when no investors are found',
        (WidgetTester tester) async {
      final client = MockClient();

      when(client.get(Uri.parse('http://localhost:8080/api/investors')))
          .thenAnswer((_) async => http.Response('[]', 200));

      await tester.pumpWidget(MaterialApp(home: InvestorsPage()));

      await tester.pumpAndSettle(); // Trigger the FutureBuilder and Wait for the Future to complete
      
      expect(find.text('No investors found'), findsOneWidget);
    });
  });
}














































































