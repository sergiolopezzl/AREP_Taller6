async function loadGetMsg() {
    try {
        const msgVar = document.getElementById("msg").value;
        const response = await fetch(`/logs?msg=${msgVar}`);

        if (!response.ok) {
            throw new Error(`Error fetching logs: HTTP status ${response.status}`);
        }

        const logsData = await response.json();
        displayLogs(logsData.logs);
    } catch (error) {
        console.error('Error fetching logs:', error);
    }
}

function displayLogs(logs) {
    const table = document.getElementById("logsTable");
    const bodyTable = table.querySelector("tbody");
    bodyTable.innerHTML = "";

    logs.forEach(log => {
        const newRow = document.createElement("tr");
        const cellDate = document.createElement("td");
        const cellDescription = document.createElement("td");

        cellDate.textContent = log.date;
        cellDescription.textContent = log.description;

        newRow.appendChild(cellDate);
        newRow.appendChild(cellDescription);
        bodyTable.appendChild(newRow);
    });
}
