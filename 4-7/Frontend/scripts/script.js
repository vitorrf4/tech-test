async function getAllTasks() {
    const response = await fetch("http://localhost:8080/tasks");
    const json = await response.json();

    createListFromTasks(json);
}

function createListFromTasks(jsonTaskList) {
    document.getElementById("task_list").innerHTML = "";

    for (let task of jsonTaskList) {
        insertTaskIntoList(task);
    }
}

function insertTaskIntoList(task) {
    const ul = document.getElementById("task_list");
    let li = document.createElement("li");

    li.innerHTML = `Title: ${task.title} | Description ${task.description} | Status: ${task.taskStatus}`;
    li.id = task.id;
    li = createButtons(li, task);

    ul.append(li);
}

function createButtons(li, task) {
    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "Delete";
    deleteButton.onclick = () => deleteTask(task.id);

    const markAsCompleteButton = document.createElement("button");
    markAsCompleteButton.innerHTML = "Mark as Completed";
    markAsCompleteButton.onclick = () => markAsComplete(task.id);

    li.append(deleteButton, markAsCompleteButton);
    return li;
}

async function markAsComplete(taskId) {
    await fetch(`http://localhost:8080/tasks/${taskId}/complete`, {
        method: 'put'
    })
        .then(() => {
            console.log(`task ${taskId} completed`);
        })
        .catch(err => {
            console.log("Error: ");
            console.log(err);
        });

}

async function deleteTask(taskId) {
    await fetch(`http://localhost:8080/tasks/${taskId}`, {
        method: 'delete',
    })
        // .then(async res => await res.json())
        .then(() => {
            getAllTasks();
        })
        .catch(err => {
            console.log("Error: ");
            console.log(err);
        });
}

async function saveTask(title, description) {
    const taskJson = JSON.stringify({title: title.value, description: description.value});

    await fetch("http://localhost:8080/tasks", {
        method: 'post',
        body: taskJson,
        headers : {"Content-Type": "application/json"}
    })
        .then(async res => await res.json())
        .then(res => {
            insertTaskIntoList(res);
        })
        .catch(err => {
            console.log("Error: ");
            console.log(err);
        });
}