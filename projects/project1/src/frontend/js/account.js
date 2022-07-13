viewDetails();

async function viewDetails(){
    console.log(principalString);
    console.log('button was clicked');
    let uID = principal.id;

    let response = await fetch(`${apiUrl}/requests`, {
        method: 'GET',
        headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
        },
    });

    if(response.status = 200){
        let data = await response.json();
        console.log(response.status);
    } else{
        console.log('Unable to login.')
    }
}