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
let counter = 0;
function build({}) {
  setCommonStyle({className:"scaffold10", width:1350, height:1000, margin:0, padding:0});
  setCommonStyle({className:"app-bar11", width:1350, height:50, margin:0, padding:30});
  setCommonStyle({className:"img12", width:30, height:30, margin:0, padding:0});
  setImageLink({className:"img12", link:"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Hamburger_icon.svg/2048px-Hamburger_icon.svg.png"});
  setCommonStyle({className:"p13", width:80, height:40, margin:0, padding:0});
  setTextAttr({className:"p13", text:"XFlutter Compiler Project", fontSize:20});
  setCommonStyle({className:"p14", width:40, height:40, margin:0, padding:0});
  setTextAttr({className:"p14", text:"", fontSize:20});
  setCommonStyle({className:"row15", width:1350, height:600, margin:10, padding:10});
  setButtonAttr({className:"button16", text:"-", enabled:true, onPress:() => {
    counter -= 1;
    build({});
  }});
  setCommonStyle({className:"sized-box17", width:40, height:0, margin:0, padding:0});
  setCommonStyle({className:"p18", width:160, height:40, margin:0, padding:0});
  setTextAttr({className:"p18", text:"Your counter is: ", fontSize:20});
  setCommonStyle({className:"p19", width:80, height:40, margin:0, padding:0});
  setTextAttr({className:"p19", text:counter, fontSize:20});
  setCommonStyle({className:"sized-box20", width:40, height:0, margin:0, padding:0});
  setButtonAttr({className:"button21", text:"+", enabled:true, onPress:() => {
    counter += 1;
    build({});
  }});
  setCommonStyle({className:"sized-box22", width:120, height:0, margin:0, padding:0});
  setButtonAttr({className:"button23", text:"Done", enabled:true, onPress:() => {
    handlePush({pageName:"Result", counter});
  }});
}
build({});
