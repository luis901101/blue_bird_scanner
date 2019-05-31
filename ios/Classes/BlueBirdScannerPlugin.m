#import "BlueBirdScannerPlugin.h"
#import <blue_bird_scanner/blue_bird_scanner-Swift.h>

@implementation BlueBirdScannerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBlueBirdScannerPlugin registerWithRegistrar:registrar];
}
@end
