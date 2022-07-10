let loginButton = document.getElementById('login');
loginButton.addEventListener('click', userLogin);

async function userLogin(){
    console.log('button was clicked');
    let username = document.getElementById('userName').value;
    let password = document.getElementById('userPass').value;

    let response = await fetch(`${apiUrl}/auth`,{
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

        /*
            persisting the User object sent back to session storage for use in other pages
            Session Storage only allows persistence of Strings so the JS Object is converted to a JSON string using JSON.stringify
        */
         sessionStorage.setItem('principal', JSON.stringify(data));
         window.location.href="./submitreq.html";
    } else{
        console.log('Unable to login.')
    }
}