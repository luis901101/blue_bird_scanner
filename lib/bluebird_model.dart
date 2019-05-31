
enum BlueBirdModel{
  ef400_500,
}

class BlueBirdModelUtils
{
  BlueBirdModelUtils.get();

  String nameOf(BlueBirdModel value) {
    return value != null ? value.toString().split(".")[1] : null;
  }
}