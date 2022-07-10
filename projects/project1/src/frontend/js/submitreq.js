let submitButton = document.getElementById('submit');
submitButton.addEventListener('click', submitTicket);

async function submitTicket(){
    console.log(principalString);
    console.log('button was clicked');
    let ticketType = document.getElementById('type').value.toUpperCase();
    let ticketDesc = document.getElementById('desc').value;
    let ticketAmount = document.getElementById('amount').value;
    let uID = principal.id;

    let response = await fetch(`${apiUrl}/requests`,{
        method: 'POST',
        headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
           'type': `${ticketType}`,
            'desc': `${ticketDesc}`,
            'amount': `${ticketAmount}`,
            'userID': `${uID}`
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