let taskListDiv;
let tasksUl;
let currentPage = '';

function init() {
    taskListDiv = document.getElementById("task_list");
    tasksUl = document.getElementById("task_ul");
}

async function renderTasks(taskStatus) {
    let response;

    if (!taskStatus)
        response = await fetch("http://localhost:8080/tasks");
    else
        response = await fetch(`http://localhost:8080/tasks/status/${taskStatus}`);


    changeTitle(taskStatus);
    currentPage = taskStatus;

    const jsonResponse = await response.json();

    createListFromTasks(jsonResponse);
}

function changeTitle(taskStatus) {
    if (!taskStatus)
        taskStatus = "All tasks";
    else {
        const titleWithUpperFirstLetter = taskStatus.charAt(0).toUpperCase() + taskStatus.slice(1) + " tasks";
        taskStatus = titleWithUpperFirstLetter;
    }

    const divTitle = document.getElementById("h2_div_title");
    divTitle.innerHTML = taskStatus;
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
    const buttonsContainer = document.createElement("div");
    buttonsContainer.classList.add("buttons-container");

    const deleteButton = document.createElement("button");
    deleteButton.innerHTML = "Delete";
    deleteButton.onclick = () => deleteTask(task.id);
    deleteButton.id = "delete-button";

    const changeStatusButton = document.createElement("button");
    changeStatusButton.innerHTML = "Change status";
    changeStatusButton.onclick = () => changeStatus(task);
    changeStatusButton.id = "status-button";

    buttonsContainer.append(changeStatusButton, deleteButton);
    li.appendChild(buttonsContainer);

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
            errorHandler(err);
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
            errorHandler(err);
        });
}

async function saveTask() {
    const title = document.getElementById("title");
    const description = document.getElementById("description");

    if (!title.value || !title.value.toString().trim() ||
        !description.value || !description.value.toString().trim()) {
        alert("Fields cannot be empty");
        return;
    }

    const taskJson = JSON.stringify({title: title.value, description: description.value});

    await fetch("http://localhost:8080/tasks", {
        method: 'post',
        body: taskJson,
        headers: {"Content-Type": "application/json"}
    })
        .then(async res => await res.json())
        .then(res => {
            insertTaskIntoList(res);
            title.value = "";
            description.value = "";
        })
        .catch(err => {
            errorHandler(err);
        });
}

function errorHandler(err) {
    console.log(`Error status: ${err.status}`);
    console.log("Error:");
    console.log(err);
}