function updateBoard(){
    let request = new XMLHttpRequest();
    let boards = document.getElementById("table");

    request.onload = () => {
        boards.innerHTML = request.responseText;
        setTimeout(updateBoard, 5000);
    }

    request.ontimeout = () => {
        boards.innerHTML = "Server timeout, trying again...";
        setTimeout(updateBoard, 200);
    }

    request.onerror = () => {
        boards.innerHTML = "No server reply, trying again...";
        setTimeout(updateBoard, 5000);
    }
    let url = "/sharedBoard/".concat(location.href.split("sharedBoard/")[1]);

    request.open("GET", url, true);
    request.timeout = 5000;
    request.send();
}