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

## Other plugins you may be interested in

- [honeywell_scanner](https://pub.dev/packages/honeywell_scanner)
- [zkc_scanner](https://pub.dev/packages/zkc_scanner)