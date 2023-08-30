'use strict';
function getData() {
  const pageHref = location.search;
  const searchParams = new URLSearchParams(pageHref.substring(pageHref.indexOf("?")));
  const data = JSON.parse(searchParams.get("data"));
  if (data) {
    console.log(data);
    for (let key in data) {
      window[key] = data[key];
    }
  }
}
function handlePush({pageName, ...data}) {
  window.location.href = `../${pageName}/${pageName}.html?data=${JSON.stringify(data)}`;
}
function setCommonStyle({className, width, height, margin, padding}) {
  const element = document.querySelector(`.${className}`);
  element.style.width = `${width}px`;
  element.style.height = `${height}px`;
  element.style.margin = `${margin}px`;
  element.style.padding = `${padding}px`;
}
function setImageLink({className, link}) {
  const image = document.querySelector(`.${className}`);
  image.setAttribute("src", `${link}`);
}
function setTextAttr({className, text, fontSize}) {
  const p = document.querySelector(`.${className}`);
  p.style.fontSize = `${fontSize}px`;
  p.innerHTML = "";
  p.appendChild(document.createTextNode(`${text}`));
}
function setButtonAttr({className, text, enabled, onPress}) {
  const button = document.querySelector(`.${className}`);
  button.innerHTML = "";
  button.appendChild(document.createTextNode(`${text}`));
  button.disabled = !enabled;
  button.onclick = onPress;
}
getData();
function build({}) {
  setCommonStyle({className:"scaffold0", width:1350, height:1000, margin:0, padding:0});
  setCommonStyle({className:"app-bar1", width:1350, height:50, margin:0, padding:30});
  setCommonStyle({className:"img2", width:30, height:30, margin:0, padding:0});
  setImageLink({className:"img2", link:"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Hamburger_icon.svg/2048px-Hamburger_icon.svg.png"});
  setCommonStyle({className:"p3", width:80, height:40, margin:0, padding:0});
  setTextAttr({className:"p3", text:"XFlutter Compiler Project", fontSize:20});
  setCommonStyle({className:"p4", width:40, height:40, margin:0, padding:0});
  setTextAttr({className:"p4", text:"", fontSize:20});
  setCommonStyle({className:"row5", width:1350, height:600, margin:10, padding:10});
  setCommonStyle({className:"p6", width:660, height:40, margin:0, padding:0});
  setTextAttr({className:"p6", text:"The Counter in the previous page was: ", fontSize:24});
  setCommonStyle({className:"p7", width:80, height:40, margin:0, padding:0});
  setTextAttr({className:"p7", text:window["counter"], fontSize:28});
  setCommonStyle({className:"sized-box8", width:80, height:0, margin:0, padding:0});
  setButtonAttr({className:"button9", text:"Back", enabled:true, onPress:() => {
    window.history.back();
  }});
}
build({});
