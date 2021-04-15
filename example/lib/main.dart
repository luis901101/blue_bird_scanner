import 'package:blue_bird_scanner/blue_bird_scanner.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> implements ScannerCallBack{
  final blueBirdScanner = BlueBirdScanner();
  String? _scannedCode = 'Empty';
  String _scannedStatus = 'Stopped';

  @override
  void initState() {
    super.initState();
    blueBirdScanner.scannerCallBack = this;
  }

  @override
  void onDecoded(String? result) {
    setState(() {
      _scannedCode = result;
    });
  }

  @override
  void onError(Exception error) {
    setState(() {
      _scannedCode = error.toString();
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('Scanner: $_scannedStatus'),
            Divider(color: Colors.transparent,),
            Text('Scanned code: $_scannedCode'),
            Divider(color: Colors.transparent,),
            ElevatedButton(
              child: Text("Start Scanner"),
              onPressed: (){
                blueBirdScanner.startScanner();
                _scannedStatus = "Started";
                setState(() {});
              },
            ),
            Divider(color: Colors.transparent,),
            ElevatedButton(
              child: Text("Stop Scanner"),
              onPressed: (){
                blueBirdScanner.stopScanner();
                _scannedStatus = "Stopped";
                setState(() {});
              },
            ),
          ],
        ),
      ),
    );
  }
}
