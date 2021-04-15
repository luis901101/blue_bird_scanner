
import 'package:flutter/foundation.dart';

enum BlueBirdModel{
  ef400_500,
}

extension BlueBirdModelUtils on BlueBirdModel
{
  String get name => describeEnum(this);
}