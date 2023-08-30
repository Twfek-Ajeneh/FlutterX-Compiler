class Product {
  Widget build() {
    return Scaffold(
        width: 1350,
        height: 1500,
        margin: 0,
        padding: 0,
        appBar: AppBar(
            width: 1350,
            height: 50,
            margin: 0,
            padding: 30,
            color: blue,
            leading: Button(
                width: 100,
                height: 40,
                margin: 0,
                padding: 0,
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
                text: getParam(key: "productName"),
                textAlignment: center,
                fontSize: 20,
                fontWeight: medium,
                color: white
            )
        ),
        body: Column(
            width: 1350,
            height: 1500,
            mainAxisAlignment: center,
            crossAxisAlignment: start,
            margin: 10,
            padding: 10,
            children: [
              SizedBox(
                  width: 0,
                  height: 120,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Image(
                  width: 300,
                  height: 300,
                  margin: 0,
                  padding: 0,
                  link: getParam(key: "url")
              ),
              SizedBox(
                  width: 0,
                  height: 30,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Text(
                  width: 600,
                  height: 60,
                  margin: 0,
                  padding: 0,
                  text: getParam(key: "description"),
                  textAlignment: center,
                  fontSize: 18,
                  fontWeight: medium,
                  color: black
              )
            ]
        )
    );
  }
}

class Home {
  Widget build() {
    return Scaffold(
        width: 1350,
        height: 1500,
        margin: 0,
        padding: 0,
        appBar: AppBar(
            width: 1350,
            height: 50,
            margin: 0,
            padding: 30,
            color: blue,
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
                text: "Products",
                textAlignment: center,
                fontSize: 20,
                fontWeight: medium,
                color: white
            )
        ),
        body: Column(
            width: 1350,
            height: 1500,
            mainAxisAlignment: start,
            crossAxisAlignment: start,
            margin: 10,
            padding: 10,
            children: [
              SizedBox(
                  width: 0,
                  height: 80,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Row(
                  width: 1350,
                  height: 250,
                  mainAxisAlignment: start,
                  crossAxisAlignment: start,
                  margin: 0,
                  padding: 0,
                  children: [
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://istore.co.na/cdn/shop/products/mbp-spacegray-gallery1-202206_7dbb769d-07c3-4b65-b4bc-bb6889333938_2048x.png?v=1656498908"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "MacBook Pro",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The MacBook Pro is a popular laptop from Apple. It features a high-resolution Retina display, powerful processors, and a sleek design. It's available in 13-inch and 16-inch sizes and is known for its excellent battery life and user-friendly operating system.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "MacBook Pro",
                                        description: "The MacBook Pro is a popular laptop from Apple. It features a high-resolution Retina display, powerful processors, and a sleek design. It's available in 13-inch and 16-inch sizes and is known for its excellent battery life and user-friendly operating system.",
                                        url: "https://istore.co.na/cdn/shop/products/mbp-spacegray-gallery1-202206_7dbb769d-07c3-4b65-b4bc-bb6889333938_2048x.png?v=1656498908"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    ),
                    SizedBox(
                        width: 20,
                        height: 0,
                        margin: 0,
                        padding: 0,
                        color: white
                    ),
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://res.cloudinary.com/canonical/image/fetch/f_auto,q_auto,fl_sanitize,c_fill,w_810,h_439/https://ubuntu.com/wp-content/uploads/75cc/XPS13-1804.png"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "Dell XPS 13",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The Dell XPS 13 is a highly-rated laptop that's known for its compact size and powerful performance. It features a 13-inch InfinityEdge display, which provides a virtually borderless viewing experience. The XPS 13 is also lightweight and durable, making it a great choice for people on the go.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "Dell XPS 13",
                                        description: "The Dell XPS 13 is a highly-rated laptop that's known for its compact size and powerful performance. It features a 13-inch InfinityEdge display, which provides a virtually borderless viewing experience. The XPS 13 is also lightweight and durable, making it a great choice for people on the go.",
                                        url: "https://res.cloudinary.com/canonical/image/fetch/f_auto,q_auto,fl_sanitize,c_fill,w_810,h_439/https://ubuntu.com/wp-content/uploads/75cc/XPS13-1804.png"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    )
                  ]
              ),
              SizedBox(
                  width: 0,
                  height: 20,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Row(
                  width: 1350,
                  height: 250,
                  mainAxisAlignment: start,
                  crossAxisAlignment: start,
                  margin: 0,
                  padding: 0,
                  children: [
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c08347485.png"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "HP Spectre x360",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The HP Spectre x360 is a versatile laptop that can be used as a traditional laptop or a tablet. It features a 360-degree hinge, which allows you to flip the screen around and use it in a variety of different modes. The Spectre x360 also boasts a premium design, long battery life, and fast performance.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "HP Spectre x360",
                                        description: "The HP Spectre x360 is a versatile laptop that can be used as a traditional laptop or a tablet. It features a 360-degree hinge, which allows you to flip the screen around and use it in a variety of different modes. The Spectre x360 also boasts a premium design, long battery life, and fast performance.",
                                        url: "https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c08347485.png"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    ),
                    SizedBox(
                        width: 20,
                        height: 0,
                        margin: 0,
                        padding: 0,
                        color: white
                    ),
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://www.pngmart.com/files/15/Apple-iPhone-12-PNG-Picture.png"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "iphone",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The iPhone is a popular mobile phone that's known for its user-friendly interface, high-quality camera, and sleek design. It's available in a range of models, from the budget-friendly iPhone SE to the high-end iPhone 13 Pro Max. The iPhone also features a powerful processor, long battery life, and access to the App Store, which offers a wide range of apps and games.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "iphone",
                                        description: "The iPhone is a popular mobile phone that's known for its user-friendly interface, high-quality camera, and sleek design. It's available in a range of models, from the budget-friendly iPhone SE to the high-end iPhone 13 Pro Max. The iPhone also features a powerful processor, long battery life, and access to the App Store, which offers a wide range of apps and games.",
                                        url: "https://www.pngmart.com/files/15/Apple-iPhone-12-PNG-Picture.png"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    )
                  ]
              ),
              SizedBox(
                  width: 0,
                  height: 20,
                  margin: 0,
                  padding: 0,
                  color: white
              ),
              Row(
                  width: 1350,
                  height: 250,
                  mainAxisAlignment: start,
                  crossAxisAlignment: start,
                  margin: 0,
                  padding: 0,
                  children: [
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://img.us.news.samsung.com/us/wp-content/uploads/2023/03/14124128/SM-A546_Galaxy-A54-5G_Awesome-Violet_Front.png"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "Samsung Galaxy",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The Samsung Galaxy is a popular Android-based mobile phone that's known for its large, high-resolution display, powerful processor, and advanced camera system. It's available in a range of models, from the budget-friendly Galaxy A series to the high-end Galaxy S21 Ultra. The Galaxy also features expandable storage.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "Samsung Galaxy",
                                        description: "The Samsung Galaxy is a popular Android-based mobile phone that's known for its large, high-resolution display, powerful processor, and advanced camera system. It's available in a range of models, from the budget-friendly Galaxy A series to the high-end Galaxy S21 Ultra. The Galaxy also features expandable storage.",
                                        url: "https://img.us.news.samsung.com/us/wp-content/uploads/2023/03/14124128/SM-A546_Galaxy-A54-5G_Awesome-Violet_Front.png"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    ),
                    SizedBox(
                        width: 20,
                        height: 0,
                        margin: 0,
                        padding: 0,
                        color: white
                    ),
                    Container(
                        width: 650,
                        height: 250,
                        margin: 0,
                        padding: 15,
                        child: Row(
                            width: 600,
                            height: 200,
                            mainAxisAlignment: start,
                            crossAxisAlignment: start,
                            margin: 0,
                            padding: 0,
                            children: [
                              Image(
                                  width: 250,
                                  height: 200,
                                  margin: 0,
                                  padding: 0,
                                  link: "https://static-www.o2.co.uk/sites/default/files/Google-Pixel-7-Pro-Snow-sku-header-140922_0.png"
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 10,
                                  padding: 0,
                                  color: white
                              ),
                              Column(
                                  width: 400,
                                  height: 400,
                                  mainAxisAlignment: start,
                                  crossAxisAlignment: start,
                                  margin: 0,
                                  padding: 0,
                                  children: [
                                    Text(
                                        width: 160,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "Google Pixel",
                                        textAlignment: start,
                                        fontSize: 18,
                                        fontWeight: bold,
                                        color: white
                                    ),
                                    Text(
                                        width: 250,
                                        height: 40,
                                        margin: 0,
                                        padding: 0,
                                        text: "The Google Pixel is a popular mobile phone that's known for its high-quality camera, fast performance, and clean, user-friendly interface. It's available in a range of models, from the budget-friendly Pixel 4a to the high-end Pixel 6 Pro. The Pixel also features a long battery life, fast charging, and access to the Google Play Store.",
                                        textAlignment: start,
                                        fontSize: 14,
                                        fontWeight: regular,
                                        color: white
                                    )
                                  ]
                              ),
                              SizedBox(
                                  width: 20,
                                  height: 0,
                                  margin: 0,
                                  padding: 0,
                                  color: blue
                              ),
                              Button(
                                  width: 150,
                                  height: 40,
                                  margin: 0,
                                  padding: 0,
                                  text: "buy",
                                  onPress: () {
                                    push Product(
                                        productName: "Google Pixel",
                                        description: "The Google Pixel is a popular mobile phone that's known for its high-quality camera, fast performance, and clean, user-friendly interface. It's available in a range of models, from the budget-friendly Pixel 4a to the high-end Pixel 6 Pro. The Pixel also features a long battery life, fast charging, and access to the Google Play Store.",
                                        url: "https://static-www.o2.co.uk/sites/default/files/Google-Pixel-7-Pro-Snow-sku-header-140922_0.png"
                                    );
                                  },
                                  color: white,
                                  enabled: true
                              )
                            ]
                        ),
                        color: blue
                    )
                  ]
              )
            ]
        )
    );
  }
}
