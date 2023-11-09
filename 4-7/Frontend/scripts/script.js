let taskListDiv;
let tasksUl;
let currentPage = '';

function init() {
    taskListDiv = document.getElementById("task_list");
    tasksUl = document.getElementById("task_ul");
}

async function renderTasks(taskStatus) {
    let response;

    if (!taskStatus) {
        response = await fetch("http://localhost:8080/tasks");
        currentPage = '';
    } else {
        response = await fetch(`http://localhost:8080/tasks/status/${taskStatus}`);
        currentPage = taskStatus;
    }
    const jsonResponse = await response.json();

    createListFromTasks(jsonResponse);
}

function createListFromTasks(jsonTaskList) {
    tasksUl.innerHTML = "";

    for (let task of jsonTaskList) {
        insertTaskIntoList(task);
    }
}

function insertTaskIntoList(task) {
    let li = document.createElement("li");

    li.innerHTML = `Title: ${task.title} | Description: ${task.description} | Status: ${task.taskStatus}`;
    li.id = task.id;
    li = createButtons(li, task);

    tasksUl.appendChild(li);
    taskListDiv.append(tasksUl);
}

function createButtons(li, task) {
    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "Delete";
    deleteButton.onclick = () => deleteTask(task.id);

    const changeStatusButton = document.createElement("button");
    changeStatusButton.innerHTML = "Change status";
    changeStatusButton.onclick = () => changeStatus(task);

    li.append(deleteButton, changeStatusButton);
    return li;
}

async function changeStatus(task) {
    let newStatus;

    if (task.taskStatus === 'COMPLETED') {
        newStatus = 'PENDING';
    } else {
        newStatus = 'COMPLETED';
    }

    await fetch(`http://localhost:8080/tasks/${task.id}/${newStatus}`, {
        method: 'put'
    })
        .then(() => {
            renderTasks(currentPage);
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
        .then(() => {
            renderTasks(currentPage);
        })
        .catch(err => {
            console.log("Error: ");
            console.log(err);
        });
}

async function saveTask() {
    const taskTitle = document.getElementById("title");
    const description = document.getElementById("description");
    const taskJson = JSON.stringify({title: taskTitle.value, description: description.value});

    await fetch("http://localhost:8080/tasks", {
        method: 'post',
        body: taskJson,
        headers: {"Content-Type": "application/json"}
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