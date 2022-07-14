let update = document.getElementById('submit');
update.addEventListener('click', updateTicket);

viewTicket();

async function updateTicket(){
    let status = document.getElementById('status').value.toUpperCase();
    let ticketID = document.getElementById('ticketid').value;

    let response = await fetch(`${apiUrl}/requests/${principal.id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'status': `${status}`,
            'ticketid': `${ticketID}`
        })
    });

    if(response.status = 200){
        window.location.href="../html/viewreqM.html";
    } else{
        console.log('Unable to update Ticket.')
    }
}



async function viewTicket(){
   let response = await fetch(`${apiUrl}/requests`, {
            method: 'GET',
            headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    
        if(response.status = 200){
            let data = await response.json();
            createTable(data);
        } else{
            console.log('Unable to view tickets.')
    }   
}



var tableDiv = document.getElementById('tableDiv');

function createTable(dataInput) {

    var table = document.createElement('table');
    var tr = document.createElement('tr');
    var td = document.createElement('th');


    //table headers
    td.innerHTML = 'Ticket ID';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Employee ID';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Ticket Type';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Ticket Description';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Ticket Amount';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Status';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Approving Manager ID';
    tr.appendChild(td);

    table.appendChild(tr);


    //table data
    for(let i = 0; i<dataInput.length;i++){
        tr = document.createElement('tr');

         //ticket id
         td = document.createElement('td');
         td.innerHTML = dataInput[i].id;
         tr.appendChild(td);
 


        //employee id
        td = document.createElement('td');
        td.innerHTML = dataInput[i].employee_id;
        tr.appendChild(td);

        //type
        td = document.createElement('td');
        td.innerHTML = dataInput[i].type;
        tr.appendChild(td);

        //ticket description
        td = document.createElement('td');
        td.innerHTML = dataInput[i].ticket_desc;
        tr.appendChild(td);

          //tciket amount
          td = document.createElement('td');
          td.innerHTML = dataInput[i].ticket_amount;
          tr.appendChild(td);

        
        //status
        td = document.createElement('td');
        if(dataInput[i].status === 'PENDING'){
            td.innerHTML = 'PENDING';
        }else{
            td.innerHTML = dataInput[i].status;
        }
        tr.appendChild(td);

        //managers id
        td = document.createElement('td');
        if(dataInput[i].status === 'PENDING'){
            td.innerHTML = '';
        }else{
            td.innerHTML = dataInput[i].manager_id;
        }
        tr.appendChild(td);

        //append to table
        table.appendChild(tr);
    }
    tableDiv.appendChild(table);
}