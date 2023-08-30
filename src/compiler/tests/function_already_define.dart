class Home {
  string x = "Hello World";

  int fun() {
    return 50;
  }

  int fun() {
    return 100;
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
              ),
              Button(
                  width: 40,
                  height: 40,
                  margin: 10,
                  padding: 10,
                  text: "click",
                  onPress: () {
                    push x(param: "Hello World");
                  },
                  color: red,
                  enabled: true
              )
            ]
        ),
        color: green
    );
  }
}