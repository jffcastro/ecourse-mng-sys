function updateBoardsList(){
    let request = new XMLHttpRequest();
    let boards = document.getElementById("boards");

    request.onload = () => {
        boards.innerHTML = request.responseText;
        setTimeout(updateBoardsList, 5000);
    }

    request.ontimeout = () => {
        boards.innerHTML = "Server timeout, trying again...";
        setTimeout(updateBoardsList, 200);
    }

    request.onerror = () => {
        boards.innerHTML = "No server reply, trying again...";
        setTimeout(updateBoardsList, 5000);
    }

    request.open("GET", "/sharedBoards", true);
    request.timeout = 5000;
    request.send();
}