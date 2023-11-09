async function getAllTasks() {
    const response = await fetch("http://localhost:8080/tasks",);
    const json = await response.json();

    console.log(json);

    createListFromTasks(json);
}

function createListFromTasks(jsonTaskList) {
    const ul = document.getElementById("task_list");
    ul.innerHTML = "";

    for (let task of jsonTaskList) {
        const li = document.createElement("li");
        li.innerHTML = `Title: ${task.title} | Description ${task.description}`;
        ul.append(li);
    }
}

function insertTaskIntoList(task) {
    const ul = document.getElementById("task_list");
    const li = document.createElement("li");

    li.innerHTML = `Title: ${task.title} | Description ${task.description}`;
    ul.append(li);
}

async function saveTask(title, description) {
    const taskJson = JSON.stringify({title: title.value, description: description.value});
    console.log(taskJson);

    const response = await fetch("http://localhost:8080/tasks", {
        method: 'post',
        body: taskJson,
        headers : {"Content-Type": "application/json"}
    });
    if (response.status === 201) {
        const taskJson = await response.json()
        insertTaskIntoList(taskJson);
    }
}