let submitButton = document.getElementById('submit');
submitButton.addEventListener('click', submitTicket);

async function submitTicket(){
    console.log(principalString);
    console.log('button was clicked');
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
        let data = await response.json();
        console.log(response.status);
        console.log(data);
    } else{
        console.log('Unable to login.')
    }
}