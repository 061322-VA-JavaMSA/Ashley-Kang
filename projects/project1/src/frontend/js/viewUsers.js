getUsers();

async function getUsers(){

    let response = await fetch(`${apiUrl}/users`, {
    });

    if(response.status == 200){
        let data = await response.json();
        console.log(data);
    } else{
        console.log('Unable to retrieve users.')
    }
}