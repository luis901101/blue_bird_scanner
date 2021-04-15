# blue_bird_scanner

A Flutter plugin for barcode scanning with BlueBird PDAs.

## How to use
```dart
final blueBirdScanner = BlueBirdScanner();
blueBirdScanner.scannerCallBack = this; // this must implement ScannerCallBack

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

blueBirdScanner.startScanner();
blueBirdScanner.stopScanner();

```