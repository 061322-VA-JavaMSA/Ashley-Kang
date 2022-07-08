let loginButton = document.getElementById('login');
loginButton.addEventListener('click', userLogin);

async function userLogin(){
    console.log('button was clicked');
    let username = document.getElementById('userName').value;
    let password = document.getElementById('userPass').value;

    let response = await fetch(`${apiUrl}/users`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'username': `${username}`,
            'password': `${password}`
        })
    });

    if(response.status = 200){
        let data = await response.json();
        console.log(response.status);
        console.log(data);
    } else{
        console.log('Unable to login.')
    }
}