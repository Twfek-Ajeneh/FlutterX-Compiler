class Home {
  string x = "Hello World";

  // int y = 100;

  Widget build() {
    return Container(
        width: y,
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
                    print "click";
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