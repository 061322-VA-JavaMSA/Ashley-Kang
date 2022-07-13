let loginButton = document.getElementById('login');
loginButton.addEventListener('click', userLogin);

async function userLogin(){
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
         sessionStorage.setItem('principal', JSON.stringify(data));
         window.location.href="../html/homepage.html";
    } else{
        console.log('Unable to login.')
    }
}