async function getAllTasks() {
    const response = await fetch("http://localhost:8080/tasks", {
    });

    // const string = await response.text();
    // const json = string === "" ? {} : JSON.parse(string);
    const json = await response.json();
    console.log(json);
}

function createListFromTasks() {

}