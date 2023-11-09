async function getAllTasks() {
    const response = await fetch("http://localhost:8080/tasks", {
    });
    const json = await response.json();

    console.log(json);

    createListFromTasks(json);
}

function createListFromTasks(jsonTaskList) {
    const ul = document.getElementById("task_list");

    for (let task of jsonTaskList) {
        const li = document.createElement("li");
        li.innerHTML = `Title: ${task.title}`;
        ul.append(li);
    }
}