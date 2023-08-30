class Result {

  Widget build() {
    return Scaffold(
        width: 1350,
        height: 1000,
        margin: 0,
        padding: 0,
        appBar: AppBar(
            width: 1350,
            height: 50,
            margin: 0,
            padding: 30,
            color: red,
            leading: Button(
                width: 150,
                height: 40,
                margin: 10,
                padding: 10,
                text: "back",
                onPress: () {
                  pop;
                },
                color: white,
                enabled: true
            ),
            trailing: Text(
                width: 40,
                height: 40,
                margin: 0,
                padding: 0,
                text: "",
                textAlignment: center,
                fontSize: 20,
                fontWeight: bold,
                color: red
            ),
            center: Text(
                width: 80,
                height: 40,
                margin: 0,
                padding: 0,
                text: "Counter Result",
                textAlignment: center,
                fontSize: 20,
                fontWeight: medium,
                color: white
            )
        ),
        body: Column(
            width: 1350,
            height: 600,
            mainAxisAlignment: center,
            crossAxisAlignment: center,
            margin: 10,
            padding: 10,
            children: [
              Image(
                  width: 300,
                  height: 150,
                  margin: 0,
                  padding: 0,
                  link: "https://cdn2.hubspot.net/hubfs/2820648/Finally_Logo_Black.png"
              ),
              SizedBox(
                  width: 0,
                  height: 30,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Text(
                  width: 660,
                  height: 40,
                  margin: 0,
                  padding: 0,
                  text: "The Counter in the previous page was: ",
                  textAlignment: center,
                  fontSize: 24,
                  fontWeight: medium,
                  color: black
              ),
              Text(
                  width: 80,
                  height: 40,
                  margin: 0,
                  padding: 0,
                  text: getParam(key: "counter"),
                  textAlignment: center,
                  fontSize: 36,
                  fontWeight: bold,
                  color: black
              )
            ]
        )
    );
  }
}

class Home {
  int counter = 0;

  Widget build() {
    return Scaffold(
        width: 1350,
        height: 1000,
        margin: 0,
        padding: 0,
        appBar: AppBar(
            width: 1350,
            height: 50,
            margin: 0,
            padding: 30,
            color: red,
            leading: Text(
                width: 40,
                height: 40,
                margin: 0,
                padding: 0,
                text: "",
                textAlignment: center,
                fontSize: 20,
                fontWeight: bold,
                color: red
            ),
            trailing: Button(
                width: 150,
                height: 40,
                margin: 10,
                padding: 10,
                text: "Done",
                onPress: () {
                  push Result(counter: counter);
                },
                color: white,
                enabled: true
            ),
            center: Text(
                width: 80,
                height: 40,
                margin: 0,
                padding: 0,
                text: "Counter",
                textAlignment: center,
                fontSize: 20,
                fontWeight: medium,
                color: white
            )
        ),
        body: Row(
            width: 1350,
            height: 600,
            mainAxisAlignment: center,
            crossAxisAlignment: center,
            margin: 10,
            padding: 10,
            children: [
              Button(
                  width: 60,
                  height: 60,
                  margin: 10,
                  padding: 10,
                  text: "-",
                  onPress: () {
                    counter = counter - 1;
                    state;
                  },
                  color: red,
                  enabled: true
              ),
              SizedBox(
                  width: 40,
                  height: 0,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Text(
                  width: 80,
                  height: 40,
                  margin: 0,
                  padding: 0,
                  text: counter,
                  textAlignment: center,
                  fontSize: 42,
                  fontWeight: medium,
                  color: black
              ),
              SizedBox(
                  width: 40,
                  height: 0,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Button(
                  width: 60,
                  height: 60,
                  margin: 10,
                  padding: 10,
                  text: "+",
                  onPress: () {
                    counter = counter + 1;
                    state;
                  },
                  color: red,
                  enabled: true
              )
            ]
        )
    );
  }
}
