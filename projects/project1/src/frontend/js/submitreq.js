let submitButton = document.getElementById('submit');
submitButton.addEventListener('click', submitTicket);

async function submitTicket(){
    let ticketType = document.getElementById('type').value.toUpperCase();
    let ticketDesc = document.getElementById('desc').value;
    let ticketAmount = document.getElementById('amount').value;

    let response = await fetch(`${apiUrl}/requests/${principal.id}`,{
        method: 'POST',
        headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
           'type': `${ticketType}`,
            'desc': `${ticketDesc}`,
            'amount': `${ticketAmount}`
        })
    });

    if(response.status = 201){
        window.location.href="../html/viewreq.html";
    } else{
        console.log('Unable to create Ticket.')
    }
}