getUsers();

async function getUsers(){

    let response = await fetch(`${apiUrl}/users`, {
        method: 'GET',
        headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
        }
    });

    if(response.status == 200){
        let data = await response.json();
        createTable(data)
    } else{
        console.log('Unable to retrieve users.')
    }
}



var tableDiv = document.getElementById('tableDiv');

function createTable(dataInput) {

    var table = document.createElement('table');
    var tr = document.createElement('tr');
    var td = document.createElement('th');


    //table headers
    td.innerHTML = 'Employee ID';
    tr.appendChild(td);

    var td = document.createElement('th');
    td.innerHTML = 'Employee Name';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Role';
    tr.appendChild(td);

    table.appendChild(tr);


    //table data
    for(let i = 0; i<dataInput.length;i++){
        tr = document.createElement('tr');


        //employee name
        td = document.createElement('td');
        td.innerHTML = dataInput[i].id;
        tr.appendChild(td);

        //employee username
        td = document.createElement('td');
        td.innerHTML = dataInput[i].user_name;
        tr.appendChild(td);

        //employee role
        td = document.createElement('td');
        td.innerHTML = dataInput[i].role;
        tr.appendChild(td);

        //append to table
        table.appendChild(tr);
    }
    tableDiv.appendChild(table);
}