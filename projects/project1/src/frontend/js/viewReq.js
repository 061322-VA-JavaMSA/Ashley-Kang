viewTicket();

async function viewTicket(){
    console.log(principalString);
    console.log('button was clicked');

    if(principal.role = 'EMPLOYEE'){
        let response = await fetch(`${apiUrl}/requests/${principal.id}`, {
            method: 'GET',
            headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    
        if(response.status = 200){
            let data = await response.json();
            console.log(response.status);
            console.log(data);
        } else{
            console.log('Unable to login.')
        }
    }else {
        let response = await fetch(`${apiUrl}/requests`, {
            method: 'GET',
            headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    
        if(response.status = 200){
            let data = await response.json();
            console.log(response.status);
            console.log(data);
        } else{
            console.log('Unable to login.')
        }
    }
    
}