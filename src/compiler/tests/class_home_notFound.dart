// class Home{
class page {
  string x = "Hello World";

  int fun() {
    return 50;
  }

  Widget build() {
    return Container(
        width: 100,
        height: 100,
        margin: 10,
        padding: 10,
        child: Column(
            width: 40,
            height: 40,
            mainAxisAlignment: center,
            crossAxisAlignment: center,
            margin: 10,
            padding: 10,
            children: [
              Text(
                  width: 40,
                  height: 40,
                  margin: 10,
                  padding: 10,
                  text: x,
                  textAlignment: center,
                  fontSize: 20,
                  fontWeight: bold,
                  color: red
              )
            ]
        ),
        color: green
    );
  }
}